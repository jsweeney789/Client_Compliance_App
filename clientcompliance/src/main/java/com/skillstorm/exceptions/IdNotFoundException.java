package com.skillstorm.exceptions;

import com.skillstorm.models.ClientRecord;
import com.skillstorm.models.User;

public class IdNotFoundException extends Exception {
	

	private static final long serialVersionUID = 1L;
	
	public IdNotFoundException()
	{
		super("Id not Found");
	}
	
	public IdNotFoundException(String message)
	{
		super(message);
	}
	
	public IdNotFoundException(String message, ClientRecord clientrecord)
	{
		super(message +" "+ clientrecord.toString());
	}
	
	public IdNotFoundException(String message, User user)
	{
		super(message +" "+ user.toString());
	}

}
