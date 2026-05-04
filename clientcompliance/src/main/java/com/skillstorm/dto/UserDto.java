package com.skillstorm.dto;

import com.skillstorm.models.User;
import com.skillstorm.types.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record UserDto(String id, String firstName, String lastName,@Email String email, 
		String phoneNumber, String password, Role role) {
	
	public static User convertToUser(UserDto userdto)
	{
		return new User(userdto.firstName,userdto.lastName,userdto.email,userdto.phoneNumber,
				userdto.password,userdto.role);
	}
	
	public static UserDto convertToDto(User user) {
		return new UserDto(user.getId(),user.getFirstName(), user.getLastName(), user.getEmail(), 
							user.getPhoneNumber(), user.getPassword(), user.getRole());
	}
	

}
