package com.it.impulseS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it.impulseS.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {	
	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);
	
	Optional<User> findByTelephoneNumber(String telephoneNumber);
	Boolean existsByTelephoneNumber(String telephoneNumber);
}
