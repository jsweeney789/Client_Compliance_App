package com.skillstorm.dto;

import com.skillstorm.models.User;
import com.skillstorm.types.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserDto(@NotBlank String firstName,@NotBlank String lastName,@Email String email,
		@Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Type in valid phone number") 
		String phoneNumber, @NotBlank String password, @NotNull Role role) {
	
	public static User convertToUser(UserDto userdto)
	{
		return new User(userdto.firstName,userdto.lastName,userdto.email,userdto.phoneNumber,
				userdto.password,userdto.role);
	}
	
	

}
