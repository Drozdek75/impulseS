package com.it.impulseS.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.it.impulseS.apiKeyUtility;

@Configuration
@PropertySource("classpath:params.properties")
public class ApikeyConfiguration {

	@Bean
	public apiKeyUtility getApiKetUtility() {
		return new apiKeyUtility();
	}

}
