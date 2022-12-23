package com.it.impulseS.configure.jwt;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtProvider {
	
	private static final Logger log = LogManager.getLogger(JwtProvider.class);
	
	public static final String issuer = "impulse";
	public static String secret;
	public static String prefix;
	public static String headerParameter;
	
	
	@Autowired
	public JwtProvider(Environment env) {
	   JwtProvider.secret = env.getProperty("impulse.jwt.secret");
	   JwtProvider.prefix = env.getProperty("impulse.jwt.prefix");
	   JwtProvider.headerParameter = env.getProperty("impulse.jwt.param");
	   if(JwtProvider.secret == null || JwtProvider.headerParameter == null || JwtProvider.prefix == null) {
		   throw new BeanInitializationException("security properties not found");
	   }
	}
	
	
	public static String createJwt(String subject, Map<String, Object> payloadClaims) {
		JWTCreator.Builder builder = JWT.create().withSubject(subject).withIssuer(issuer);
		final DateTime now = DateTime.now();
		builder.withIssuedAt(now.toDate()).withExpiresAt(now.plusDays(10).toDate());
		
		if(payloadClaims.isEmpty()) {
		     log.warn("you are building a JWT without header claims");
		}
		
		for(Map.Entry<String, Object> entry: payloadClaims.entrySet()) {
			builder.withClaim(entry.getKey(), entry.getValue().toString());
		}
		
		return builder.sign(Algorithm.HMAC256(JwtProvider.secret));
	}
	
	
	public static DecodedJWT verifyJWT(String jwt) {
		return JWT.require(Algorithm.HMAC256(JwtProvider.secret)).build().verify(jwt);
	}
	
	

}
