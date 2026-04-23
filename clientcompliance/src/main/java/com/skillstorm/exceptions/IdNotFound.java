package com.skillstorm.exceptions;

import com.skillstorm.models.ClientRecord;
import com.skillstorm.models.User;

public class IdNotFound extends Exception {
	

	private static final long serialVersionUID = 1L;
	
	public IdNotFound()
	{
		super("Id not Found");
	}
	
	public IdNotFound(String message)
	{
		super(message);
	}
	
	public IdNotFound(String message, ClientRecord clientrecord)
	{
		super(message +" "+ clientrecord.toString());
	}
	
	public IdNotFound(String message, User user)
	{
		super(message +" "+ user.toString());
	}

}
