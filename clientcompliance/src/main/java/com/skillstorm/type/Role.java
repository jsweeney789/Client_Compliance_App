package com.skillstorm.type;

public enum Role {
	Compliance_Officer("Compliance Officer"),
	Relationship_Manager("Relationship Manager"),
	ADMINISTATOR("Administator");

	
	private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
	

}
