package com.skillstorm.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.models.OnboardingCase;

@Repository
public interface OnboardingCaseRepository extends MongoRepository<OnboardingCase, String>{

}
