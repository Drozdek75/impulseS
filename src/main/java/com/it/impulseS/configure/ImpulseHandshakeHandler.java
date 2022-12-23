package com.it.impulseS.configure;

import java.security.Principal;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ImpulseHandshakeHandler extends DefaultHandshakeHandler {
	
	private final ObjectMapper mapper = new ObjectMapper();
	private final Base64.Decoder decoder = Base64.getUrlDecoder();
	
	
	
		
	
	private String stompId="";

	@Override
	protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
			  Map<String, Object> attributes) {
		
		  String uriRequest = request.getURI().toString();
		  System.out.println("uriRequest: "+uriRequest);
		  uriRequest = uriRequest.substring(uriRequest.indexOf("?")+1, uriRequest.length());
		  
		  uriRequest = uriRequest.replaceAll("%20", " ");
		  uriRequest = uriRequest.substring(uriRequest.indexOf("=")+1, uriRequest.length());
		  
		  
		  System.out.println("u-->"+uriRequest);
		  
		  
				
		
		
			
	           String token = uriRequest.replace("Bearer ", "");
	           System.out.println("u-> "+token);
	            
			    String[] chunks = token.split("\\.");
			    String payload = new String(decoder.decode(chunks[1]));
			    JSONObject pay  = new JSONObject(payload);
			    Iterator iter = pay.keys();
			    while(iter.hasNext()) {
			    	String key = (String)iter.next();
			    	if(key.equals("user")) {
			    		Object user  = pay.get(key);
			    		 JSONObject userS  = new JSONObject(user.toString());
			    	     this.stompId = userS.get("telephoneNumber").toString();			    	    
			    	}			    	
			    }			    			    			    
		
		
		
		
		System.out.println("handshake: "+this.stompId);
		return new StompPrincipal(this.stompId);
		
	}

}
