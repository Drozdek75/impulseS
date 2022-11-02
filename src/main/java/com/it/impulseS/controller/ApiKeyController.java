package com.it.impulseS.controller;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Random;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.it.impulseS.model.requestsPending.RequestPendingRepository;
import com.it.impulseS.model.requestsPending.RequestsPending;
import com.it.impulseS.model.requestsPending.StatusPendingRequest;
import com.it.impulseS.requestStatus.RequestStatus;

import ch.qos.logback.core.status.Status;

import java.util.Optional;


@RestController
@RequestMapping("/apikey")
public class ApiKeyController {

	@Autowired
	private apiKeyUtility apikey;
	
	@Autowired
	private RequestPendingRepository requestsPending;

	@Autowired
	private EmailService emailService;

	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/getApiKey/{telephoneNumber}")
	public RequestStatus getApiKey(@PathVariable("telephoneNumber") String telephoneNumber) {
		String key = this.apikey.generateMD5HashValue(telephoneNumber);
		Random random = new Random(); 
		
		//controllo che non ci sia altro codice pendente con lo stesso numero di telefono
		Optional<RequestsPending> pending = this.requestsPending.findExsistTelephoneNumber(telephoneNumber);
		if(pending.isPresent()) {
			this.requestsPending.delete(pending.get());
		}
		
		int code = (int)Math.floor(Math.random()*(999999-100000+1)+100000);
		
		this.emailService.sendHtmlMessage("crivellerpietro@gmail.com", "codice attivazione impulse",
				"<h3>Ciao! ecco il tuo codice di attivazione, inseriscilo nel campo codice attivazione per \r\n"
				+ "        poter continuare la procedura di registrazione del tuo profilo utente...\r\n"
				+ "   </h3>\r\n"
				+ "   <h2 style='font-weigth: bolder'>"+code+"</h2>");
				
		this.requestsPending.save(new RequestsPending(telephoneNumber, code, key,"", false));

		return new RequestStatus("control code sen by email", 7, LocalDateTime.now());
	}
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/register/{telephoneNumber}/{code}")
	public ResponseEntity<StatusPendingRequest> register(@PathVariable("telephoneNumber") String telephoneNumber,  @PathVariable("code") int codeV) {
		StatusPendingRequest status = new StatusPendingRequest();	
		HttpStatus resStatus = null;
		System.out.println(codeV);
	    Optional<RequestsPending> req = this.requestsPending.findByTelephoneAndCode(telephoneNumber, codeV);
	    if(req.isPresent()) {
	    	status.setApiKey(req.get().getApiKey());
	    	status.setCodeResponse(7);
	    	status.setMessage("Operation performed successfully...");
	    	status.setSuccess(true);
	    	status.setDate(LocalDateTime.now());
	    	resStatus = HttpStatus.OK;
	    	//this.requestsPending.delete(req.get());
	    	req.get().setCtrl(true);	    	
	    }
	    else {
	    	status.setCodeResponse(-1);
	    	status.setMessage("Il codice immesso non è valido");
	    	status.setDate(LocalDateTime.now());
	    	status.setSuccess(false);
	    	resStatus = HttpStatus.BAD_REQUEST;
	    }
		
		return ResponseEntity.status(resStatus).body(status);
	}
	
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/addNewAccount")
	public ResponseEntity createAccount(@RequestBody UserDTO user) {
	    RequestStatus status = new RequestStatus();
	    User userPersist = new User();
	    
	    
	    
		return ResponseEntity.status(HttpStatus.OK).body(status);
	}
	

}
