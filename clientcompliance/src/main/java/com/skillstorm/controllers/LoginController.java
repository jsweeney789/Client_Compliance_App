package com.skillstorm.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.dto.UserLogin;
import com.skillstorm.models.User;
import com.skillstorm.repositories.UserRepository;
import com.skillstorm.security.CustomUserDetails;
import com.skillstorm.security.JwtUtil;

@RestController
@RequestMapping("/api/login")
public class LoginController {
	
	private UserRepository repository;
    private PasswordEncoder encoder;
    private JwtUtil jwtUtil;
    
    public LoginController(UserRepository repo, PasswordEncoder encoder,JwtUtil jwtUtil) {
        this.repository = repo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }
 
    // this method takes in a user's username nad password via a body object
    // then encrypts the password and stores the user in the db
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserLogin dto) {
        User user = new User(dto.email(), this.encoder.encode(dto.password()));
        
       // this.repository.save(user);
        //return ResponseEntity.status(HttpStatus.CREATED).body("User registered!");
        return ResponseEntity.status(HttpStatus.CREATED).body(jwtUtil.generateToken(new CustomUserDetails(user)));
    }

}
