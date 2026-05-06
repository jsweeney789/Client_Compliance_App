package com.skillstorm.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import com.skillstorm.models.OnboardingCase;
import com.skillstorm.services.OnboardingCaseService;

@RestController
@RequestMapping("/api/cases")
public class OnboardingCaseController {
    private final OnboardingCaseService service; 

    public OnboardingCaseController(OnboardingCaseService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<OnboardingCase> createOnboardingCase(@Valid @RequestBody OnboardingCase newCase) {
        OnboardingCase onboardingCase = service.saveCase(newCase);
        if (onboardingCase == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(onboardingCase, HttpStatus.CREATED);

    }

    @GetMapping()
    public ResponseEntity<List<OnboardingCase>> getAllOnboardingCases() {
        List<OnboardingCase> cases = service.getAllCases();
        return new ResponseEntity<List<OnboardingCase>>(cases, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OnboardingCase> getOnboardingCaseById(@PathVariable String id) {
        
        OnboardingCase onboardingCase;
        try {
            onboardingCase = service.getOnboardingCaseById(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        if (onboardingCase == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(onboardingCase, HttpStatus.OK);
    }    

    @GetMapping("/client/{id}")
    public ResponseEntity<OnboardingCase> getOnboardingCaseByClientId(@PathVariable String clientId) {
        OnboardingCase onboardingCase = service.getCasesByClientId(clientId);
        return new ResponseEntity<OnboardingCase>(onboardingCase, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OnboardingCase> updateOnboardingCase(@PathVariable String id, @Valid @RequestBody OnboardingCase newCase) {
        OnboardingCase onboardingCase = service.saveCase(newCase);
        if (onboardingCase == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(onboardingCase, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOnboardingCase(@PathVariable String id) {
        boolean foundCase;
        try {
            foundCase = service.deleteOnboardingCase(id);
        } catch (NoSuchElementException e) {
            foundCase = false;
            return new ResponseEntity<>(foundCase, HttpStatus.NOT_FOUND);
        }
        if (!foundCase) {
            return new ResponseEntity<>(foundCase, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundCase, HttpStatus.NO_CONTENT);
    }
}
