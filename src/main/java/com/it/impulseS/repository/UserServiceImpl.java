package com.it.impulseS.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.impulseS.model.User;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserRepository userRepo;
	
	
	
	public Optional<User> findByTelephoneNumber(String number){
		return this.userRepo.findByTelephoneNumber(number);
	}
	

}
