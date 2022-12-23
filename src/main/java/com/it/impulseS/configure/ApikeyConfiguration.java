package com.it.impulseS.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.it.impulseS.apiKeyUtility;
import com.it.impulseS.repository.UserRepository;

@Configuration
@PropertySource("classpath:params.properties")
public class ApikeyConfiguration {

	@Bean
	public apiKeyUtility getApiKetUtility() {
		return new apiKeyUtility();
	}
	
	
	
	
	

}
