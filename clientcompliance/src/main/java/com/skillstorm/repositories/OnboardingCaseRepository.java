package com.skillstorm.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.models.OnboardingCase;
import java.util.List;


@Repository
public interface OnboardingCaseRepository extends MongoRepository<OnboardingCase, String>{
    List<OnboardingCase> findByClientId(String clientId);
}
