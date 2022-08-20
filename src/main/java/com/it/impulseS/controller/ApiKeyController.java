package com.it.impulseS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.impulseS.apiKeyUtility;

@RestController
@RequestMapping("/apikey")
public class ApiKeyController {
	
	@Autowired
	private apiKeyUtility apikey;
	
	@GetMapping("/getApiKey/{telephoneNumber}")
	public String getApiKey(@PathVariable("telephoneNumber") String telephoneNumber) {		
		return this.apikey.generateMD5HashValue(telephoneNumber);			
	}

}
