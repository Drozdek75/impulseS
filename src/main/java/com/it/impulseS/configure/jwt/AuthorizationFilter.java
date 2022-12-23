package com.it.impulseS.configure.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.it.impulseS.model.User;
import com.it.impulseS.repository.UserRepository;

public class AuthorizationFilter extends BasicAuthenticationFilter {
	
	
	private final UserRepository userRepo;
	private final ObjectMapper mapper;
	

	public AuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepo) {
		super(authenticationManager);
		this.mapper = new ObjectMapper();
		this.userRepo = userRepo;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("call filter before");
		final String header = request.getHeader(JwtProvider.headerParameter);
		System.out.println(JwtProvider.prefix);
		if(header != null && header.startsWith(JwtProvider.prefix)) {
			System.out.println("call filter before inside");
			final DecodedJWT decode = JwtProvider.verifyJWT(header.replace(JwtProvider.prefix, ""));
			final ObjectNode userNode = this.mapper.readValue(decode.getClaim("user").asString(), ObjectNode.class);
			final User user = this.mapper.convertValue(userNode, User.class);
			
			System.out.println(user.getTelephoneNumber());
			this.userRepo.findByTelephoneNumber(user.getTelephoneNumber()).ifPresent(element -> {
				SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, null));
			});
		}
		
		chain.doFilter(request, response);
	
}
	
}
