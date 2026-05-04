package com.skillstorm.types;

public enum ClientType {
	
	INDIVIDUAL("Individual"),
	CORPORATE("Corporate"),
	INSTITUTIONAL("Institutional");

	
	private final String type;

    ClientType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
	
	
	

}
