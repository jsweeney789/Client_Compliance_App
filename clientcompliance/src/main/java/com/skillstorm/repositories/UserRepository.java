package com.skillstorm.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.skillstorm.models.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	Optional<User> findByEmail(String username); 
}
