package com.it.impulseS.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.impulseS.apiKeyUtility;
import com.it.impulseS.utility.data.Datum;
import com.it.impulseS.utility.data.NationsOBJ;;


@RestController
@RequestMapping("/register")
public class registerController {
	
	@Autowired
	private apiKeyUtility apikey;
	 
	
	
	@GetMapping("/obj")
	public void objJson() {
		
		NationsOBJ nobj=null;
		
		try {
			URI jsonUrl = new URI("https://api.dhsprogram.com/rest/dhs/countries");
			ObjectMapper mapper = new ObjectMapper();
			try {
				nobj = mapper.readValue(new URL("https://countriesnow.space/api/v0.1/countries"), NationsOBJ.class);
			} catch (StreamReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatabindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		   for(Datum d : nobj.getData()) {
			   System.out.println(d.country);
		   }
		
	}
	
	

}
