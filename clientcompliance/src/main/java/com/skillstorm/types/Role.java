package com.skillstorm.types;

public enum Role {
	COMPLIANCE_OFFICER("Compliance Officer"),
	RELATIONSHIP_MANAGER("Relationship Manager"),
	ADMINISTRATOR("Administrator"),
	BASIC_USER("Basic User");

	
	private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
	

}
