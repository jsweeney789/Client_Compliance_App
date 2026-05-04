package com.skillstorm.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skillstorm.dto.UserDto;
import com.skillstorm.exceptions.IdNotFoundException;
import com.skillstorm.models.User;
import com.skillstorm.repositories.UserRepository;

@Service
public class UserService {
	
private final UserRepository userrepo;
private PasswordEncoder encoder;
	
	public UserService(UserRepository userrepo, PasswordEncoder encoder)
	{
		this.userrepo = userrepo;
		this.encoder = encoder;
	}
	
	//Get by Id
	public User getUser(String id) throws IdNotFoundException
	{
		return userrepo.findById(id).orElseThrow(()->new IdNotFoundException("User Not Found"));
		
	}
	
	//Get All
	public List<User> getAllUsers()
	{
		return userrepo.findAll().stream().filter(user-> !user.isDeleted()).collect(Collectors.toList());
		
	}
	
	//Add
		public User addUser(UserDto newuser)
		{
			User user = UserDto.convertToUser(newuser);
			user.setPassword(this.encoder.encode(newuser.password()));
			return this.userrepo.save(user);
				
			
		}
	
	
	//Update
	public User updateUser(String id, UserDto newuser) throws IdNotFoundException
	{
		User user = userrepo.findById(id).orElseThrow(()->new IdNotFoundException("User Not Found"));
		if(user.isDeleted())
			throw new IdNotFoundException("This user is deleted: ",user);
		User holduser = UserDto.convertToUser(newuser);
		holduser.setId(user.getId());
		return this.userrepo.save(holduser);
			
		
	}
	
	//Delete Record
	public User deleteUser(String id) throws IdNotFoundException
	{
		User user = userrepo.findById(id).orElseThrow(()->new IdNotFoundException("User Not Found"));
		if(user.isDeleted())
			throw new IdNotFoundException("This user is deleted: ",user);
		user.setDeleted(true);
		return this.userrepo.save(user);	
	}

}
