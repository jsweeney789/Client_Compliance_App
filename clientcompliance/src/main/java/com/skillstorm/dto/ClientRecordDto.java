package com.skillstorm.dto;

import com.skillstorm.models.ClientRecord;
import com.skillstorm.types.ClientType;
import com.skillstorm.types.CountryDomicile;
import com.skillstorm.types.IndustrySector;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClientRecordDto(@NotBlank String firstName, @NotBlank String lastName, 
		@NotNull ClientType type, @NotNull IndustrySector sector, @NotNull CountryDomicile domicile,
		@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Type in valid phone number")
		String phoneNumber, @Email String email) {
	
	
	public static ClientRecord convertToClientRecord(ClientRecordDto clientrecorddto)
	{
		return new ClientRecord(clientrecorddto.firstName, clientrecorddto.lastName, 
				clientrecorddto.type, clientrecorddto.sector, clientrecorddto.domicile,
				clientrecorddto.phoneNumber, clientrecorddto.email);
	}
	
	
	

}
