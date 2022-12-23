package com.it.impulseS.configure.jwt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.it.impulseS.model.User;
import com.it.impulseS.model.requestsPending.RequestPendingRepository;
import com.it.impulseS.model.requestsPending.RequestsPending;
import com.it.impulseS.repository.UserRepository;

@Service
public class AutehnticationService {
	
	
	@Autowired
	private RequestPendingRepository requestRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String login(String telephoneNumber, String apiKey) {
		System.out.println(apiKey);
	    User user = this.userRepo.findByTelephoneNumber(telephoneNumber).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		System.out.println(user.getPublicKey()+"  -  "+apiKey);
		//if(!apiKey.equals(user.getPublicKey())) {
		if(!this.passwordEncoder.matches(apiKey, user.getPublicKey())) {
			System.out.println("non uguali");
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
		}
		
		UserClaim userClaim = new UserClaim(user.getTelephoneNumber(), user.getName());
		
		ObjectNode userNode = new ObjectMapper().convertValue(userClaim, ObjectNode.class);
		userNode.remove("pubblicKey");	
		
		Map claimMap = new HashMap<>(0);
		claimMap.put("user", userNode);
		return JwtProvider.createJwt(telephoneNumber, claimMap);
	}
	
}
