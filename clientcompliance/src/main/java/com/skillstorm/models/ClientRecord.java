package com.skillstorm.models;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.skillstorm.types.ClientType;
import com.skillstorm.types.CountryDomicile;
import com.skillstorm.types.IndustrySector;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Document(collection="client_records")
public class ClientRecord {
	@Id
	String id;
	@NotBlank
	String firstName;
	@NotBlank
	String lastName;
	@NotNull
	ClientType type;
	@NotNull
	IndustrySector sector;
	@NotNull
	CountryDomicile domicile;
	@Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Type in valid phone number")
	String phoneNumber;
	@Email
	String email;
	Boolean isDeleted;
	

	public ClientRecord() {
		super();
		this.isDeleted = false;
	}
	
	public ClientRecord(String firstName, String lastName, ClientType type, IndustrySector sector,
			CountryDomicile domicile, String phoneNumber, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
		this.sector = sector;
		this.domicile = domicile;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.isDeleted = false;
	}
	
	public ClientRecord(String id, String firstName, String lastName, ClientType type, IndustrySector sector,
			CountryDomicile domicile, String phoneNumber, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
		this.sector = sector;
		this.domicile = domicile;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.isDeleted = false;
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
	public ClientType getType() {
		return type;
	}
	public void setType(ClientType type) {
		this.type = type;
	}
	public IndustrySector getSector() {
		return sector;
	}
	public void setSector(IndustrySector sector) {
		this.sector = sector;
	}
	public CountryDomicile getDomicile() {
		return domicile;
	}
	public void setDomicile(CountryDomicile domicile) {
		this.domicile = domicile;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "ClientRecord [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", type=" + type
				+ ", sector=" + sector + ", domicile=" + domicile + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", isDeleted=" + isDeleted + "]";
	}
	
	
	
	
	
	

}
