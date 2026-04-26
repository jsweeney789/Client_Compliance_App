package com.skillstorm.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.skillstorm.dto.UserDto;
import com.skillstorm.exceptions.IdNotFoundException;
import com.skillstorm.models.User;
import com.skillstorm.repositories.UserRepository;

@Service
public class UserService {
	
private final UserRepository userrepo;
	
	public UserService(UserRepository userrepo)
	{
		this.userrepo = userrepo;
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
		
			return this.userrepo.save(UserDto.convertToUser(newuser));
				
			
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
