package com.skillstorm.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.models.OnboardingCase;
import com.skillstorm.repositories.OnboardingCaseRepository;

@Service
public class OnboardingCaseService {
    private final OnboardingCaseRepository repo;
    public OnboardingCaseService(OnboardingCaseRepository repo) {
        this.repo = repo;
    }

    public OnboardingCase saveCase(OnboardingCase caseToSave) {
        return this.repo.save(caseToSave);
    }

    public OnboardingCase getOnboardingCaseById(String id) throws NoSuchElementException {
        Optional<OnboardingCase> caseFound = this.repo.findById(id);
        if (caseFound.isPresent()) {
            return caseFound.get();
        } else {
            throw new NoSuchElementException("No onboarding case with id: " + id);
        }
        
    }

    public List<OnboardingCase> getAllCases() {
        return this.repo.findAll();
    }

    public boolean deleteOnboardingCase(String idToDelete) throws NoSuchElementException {
        OnboardingCase caseToDelete = getOnboardingCaseById(idToDelete);
        repo.delete(caseToDelete);
        return true;
    }

    public OnboardingCase getCasesByClientId(String clientId) {
        Optional<OnboardingCase> caseFound = this.repo.findById(clientId);
        if (caseFound.isPresent()) {
            return caseFound.get();
        } else {
            throw new NoSuchElementException("No onboarding case with id: " + clientId);
        }
        
    }
    
}
