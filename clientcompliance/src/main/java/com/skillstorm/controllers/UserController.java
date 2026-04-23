package com.skillstorm.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.skillstorm.exceptions.IdNotFoundException;
import com.skillstorm.dto.UserDto;
import com.skillstorm.models.User;
import com.skillstorm.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
private final UserService userservice;
	
	public UserController(UserService userservice)
	{
		this.userservice =  userservice;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable String id)
	{
		try {
			return ResponseEntity.ok(this.userservice.getUser(id));
		} catch (IdNotFoundException exception) {
			
			return ResponseEntity.notFound().header("message", exception.getMessage()).build(); 
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<User>> users()
	{
		return ResponseEntity.ok(this.userservice.getAllUsers());
	}
	
	
	
	@PostMapping()
	public ResponseEntity<User> addUser(@Valid @RequestBody UserDto userdto)
	{
	
			return ResponseEntity.ok(this.userservice.addRecord(userdto));
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserDto userdto)
	{
		try {
			return ResponseEntity.ok(this.userservice.updateRecords(id, userdto));
		} catch (IdNotFoundException exception) {
			
			return ResponseEntity.notFound().header("message", exception.getMessage()).build(); 
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable String id)
	{
		try {
			return ResponseEntity.ok(this.userservice.deleteRecords(id));
		} catch (IdNotFoundException exception) {
			
			return ResponseEntity.notFound().header("message", exception.getMessage()).build(); 
		}
	}
	
	

}
