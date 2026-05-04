package com.skillstorm.dto;

import org.springframework.data.annotation.Id;

import com.skillstorm.models.OnboardingCase;
import com.skillstorm.types.ClientType;
import com.skillstorm.types.CountryDomicile;
import com.skillstorm.types.IndustrySector;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClientRecordOnboardDto(String id, String firstName, String lastName, 
		ClientType type, IndustrySector sector, CountryDomicile domicile,
		String phoneNumber, String email, OnboardingCase boardcase) {
	
	
	
	

}
