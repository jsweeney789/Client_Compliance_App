package com.skillstorm.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.models.OnboardingCase;
import com.skillstorm.models.User;

@Repository
public interface OnboardingCaseRepository extends MongoRepository<OnboardingCase, String>{

	
	Optional<OnboardingCase> findByClientId(String id); 
}
