package com.it.impulseS.controller;

import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.apache.tomcat.util.json.JSONParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.google.gson.Gson;
import com.it.impulseS.apiKeyUtility;
import com.it.impulseS.mailPack.EmailService;
import com.it.impulseS.model.User;
import com.it.impulseS.model.UserDTO;
import com.it.impulseS.model.UserDetailsResponse;
import com.it.impulseS.model.UserRequestContactsDTO;
import com.it.impulseS.model.requestsPending.RequestPendingRepository;
import com.it.impulseS.model.requestsPending.RequestsPending;
import com.it.impulseS.model.requestsPending.StatusPendingRequest;
import com.it.impulseS.repository.UserRepository;
import com.it.impulseS.requestStatus.RequestStatus;
import com.it.impulseS.configure.jwt.AutehnticationService;

import ch.qos.logback.core.status.Status;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Base64;

@RestController
@RequestMapping("/apikey")
public class ApiKeyController {

	@Autowired
	private apiKeyUtility apikey;

	@Autowired
	private RequestPendingRepository requestsPending;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AutehnticationService authService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/getApiKey/{telephoneNumber}")
	public RequestStatus getApiKey(@PathVariable("telephoneNumber") String telephoneNumber) {
		String key = this.apikey.generateMD5HashValue(telephoneNumber);
		Random random = new Random();
		// controllo che non ci sia altro codice pendente con lo stesso numero di
		// telefono
		Optional<RequestsPending> pending = this.requestsPending.findExsistTelephoneNumber(telephoneNumber);
		if (pending.isPresent()) {
			this.requestsPending.delete(pending.get());
		}
		int code = (int) Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
		this.emailService.sendHtmlMessage("syseng75@gmail.com", "codice attivazione impulse",
				"<h3>Ciao! ecco il tuo codice di attivazione, inseriscilo nel campo codice attivazione per \r\n"
						+ "        poter continuare la procedura di registrazione del tuo profilo utente...\r\n"
						+ "   </h3>\r\n" + "   <h2 style='font-weigth: bolder'>" + code + "</h2>");

		this.requestsPending.save(new RequestsPending(telephoneNumber, code, key, "", false));
		return new RequestStatus("control code sen by email", 7, LocalDateTime.now(), "");
	}
	
	
	
	

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/register/{telephoneNumber}/{code}")
	public ResponseEntity<StatusPendingRequest> register(@PathVariable("telephoneNumber") String telephoneNumber,
			@PathVariable("code") int codeV) {
		StatusPendingRequest status = new StatusPendingRequest();
		HttpStatus resStatus = null;
		System.out.println(codeV);
		Optional<RequestsPending> req = this.requestsPending.findByTelephoneAndCode(telephoneNumber, codeV);
		if (req.isPresent()) {
			status.setApiKey(req.get().getApiKey());
			status.setCodeResponse(7);
			status.setMessage("Operation performed successfully...");
			status.setSuccess(true);
			status.setDate(LocalDateTime.now());
			resStatus = HttpStatus.OK;
			// this.requestsPending.delete(req.get());
			req.get().setCtrl(true);
			this.requestsPending.save(req.get());
		} else {
			status.setCodeResponse(-1);
			status.setMessage("Il codice immesso non è valido");
			status.setDate(LocalDateTime.now());
			status.setSuccess(false);
			resStatus = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(resStatus).body(status);
	}
	
	
	
	
	/*
	 add a new account, model UserDTO 	 
	 */

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(path = "/addNewAccount")
	public ResponseEntity<RequestStatus> createAccount(@RequestBody UserDTO user) {
		System.out.println("dentro a create account " + user.getImageProfile());

		RequestStatus status = new RequestStatus();
		status.setStatusCode(HttpStatus.OK.value());
		User userPersist = new User();
		userPersist.setPublicKey(this.encoder.encode(user.getApiKey()));
		userPersist.setCreationDate(new Date());
		userPersist.setDateOfBirth(user.getDateOfBirth());
		userPersist.setEmail(user.getEmail());
		userPersist.setLastName(user.getLastName());
		userPersist.setName(user.getName());
		userPersist.setNation(null);
		userPersist.setPassword(this.encoder.encode(user.getPassword()));
		userPersist.setShortMessage(user.getShortMessage());
		userPersist.setTelephoneNumber(user.getTelephoneNumber());
		
		try {
			userPersist.setImage(this.base64Encode(user.getImageProfile()));
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		
		try {
			this.userRepo.save(userPersist);
		}catch(Exception exc) {
			status.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

		status.setToken(this.authService.login(user.getTelephoneNumber(), user.getApiKey()));
        System.out.println(status.getToken());
		return ResponseEntity.status(status.getStatusCode()).body(status);
	}
	
	
	
	
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/getUserDetail/{id}")
	public ResponseEntity<UserDTO> getUserDetail(@PathVariable("id") long id) {
	   UserDTO user = new UserDTO();
	   User userFind = userRepo.findById(id).get();	  
	   try {
		user.setImageProfile(this.base64Decode(userFind.getImage()));
	} catch (UnsupportedEncodingException e) {		
		e.printStackTrace();
	}	   
	   return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/getContactsDetail")
	public ResponseEntity<UserDetailsResponse> getDetailContacts(@RequestBody UserRequestContactsDTO urcDTO) {
		System.out.println("dentro a contacts detail");
		System.out.println(urcDTO.getPhoneNumbers());
		UserDetailsResponse response = new UserDetailsResponse();
		
		
		for(String tn: urcDTO.getPhoneNumbers()) {
			try {
			User user = this.userRepo.findByTelephoneNumber(tn).get();	
			
				response.getUserList().add(new UserDTO(user.getTelephoneNumber(), this.base64Decode(user.getImage()), user.getShortMessage()));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(NoSuchElementException exc) {
			//	exc.printStackTrace();
			}
	    }
				
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/ctrlEmail/{email}")
	public boolean ctrlEmail(@PathVariable("email") String email) {
		boolean status = true;
		status = this.userRepo.findByEmail(email).isPresent();		
		return status;
	}
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/ctrlPhone/{phoneNumber}")
	public boolean ctrlPhone(@PathVariable("phoneNumber") String phoneNumber) {
		boolean status = true;
		status = this.userRepo.findByTelephoneNumber(phoneNumber).isPresent();
		return status;
	}
	
	
	
	

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/login/{tn}/{apk}")
	public String login(@PathVariable("tn") String tn, @PathVariable("apk") String apk) {
		System.out.println(apk);
		return this.authService.login(tn, apk);
	}

	public byte[] base64Encode(String base64Data) throws UnsupportedEncodingException {
		byte[] retData = null;
		BufferedImage image= null;
		
	   retData = Base64.getDecoder().decode(base64Data);
		
		return retData;
		
	}
	
	
	public String base64Decode(byte[] data) throws UnsupportedEncodingException {
		String base64Ret = Base64.getEncoder().encodeToString(data);
		
		System.out.println(base64Ret);
		return base64Ret;
	}
	
	
	

}
