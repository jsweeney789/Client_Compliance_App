package com.skillstorm.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.skillstorm.types.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
@Document(collection="users")
public class User {
	
	String id;
	@NotBlank
	String firstName;
	@NotBlank
	String lastName;
	@Email
	String email;
	@Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Type in valid phone number")
	String phoneNumber;
	@NotBlank
	String password;
	Boolean isDeleted;
	@NotNull
	Role role;
	
	public User() {
		super();
		this.isDeleted = false;
	}
	
	public User(String email, String password)
	{
		this.email = email;
		this.password = password;
	}
	
	public User(String id, String firstName, String lastName, String email, String phoneNumber,String password, Role role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.isDeleted = false;
		this.role = role;
	}
	
	public User(String firstName, String lastName, String email, String phoneNumber,String password, Role role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.isDeleted = false;
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", password=" + password + ", isDeleted=" + isDeleted + ", role="
				+ role + "]";
	}
	
	
	

}
