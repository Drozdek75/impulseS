package com.it.impulseS.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.it.impulseS.model.websocket.StompMessage;

@RestController
public class WebsocketController {
	
	@Autowired	private SimpMessageSendingOperations messagingTemplate;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@MessageMapping("/message")
	public void senMessageFormClient(Principal principal, StompMessage message) throws Exception {
		System.out.println("stomp message: "+message.getTo()+" principal: "+principal.getName());
	    messagingTemplate.convertAndSendToUser(message.getTo(), "/topic", message.getMessage());
	
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@MessageMapping("/saluta")
	public void saluta() {
		System.out.println("Ciao William Drozdek");
	}
	
	
	

}
