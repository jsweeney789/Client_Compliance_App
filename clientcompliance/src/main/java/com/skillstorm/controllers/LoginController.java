package com.skillstorm.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.dto.UserDto;
import com.skillstorm.dto.UserLogin;
import com.skillstorm.models.User;
import com.skillstorm.repositories.UserRepository;
import com.skillstorm.security.CustomUserDetails;
import com.skillstorm.security.JwtUtil;
import com.skillstorm.services.UserService;

@RestController
@RequestMapping("/api/login")
public class LoginController {
	
	private UserService service;
    private PasswordEncoder encoder;
    private AuthenticationManager authManager;
    private JwtUtil jwtUtil;
    
    public LoginController(UserService userService, PasswordEncoder encoder,JwtUtil jwtUtil, AuthenticationManager authManager) {
        this.service = userService;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }
 
    // this method takes in a user's username nad password via a body object
    // then encrypts the password and stores the user in the db
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserLogin dto) {
        User user = new User(dto.email(), this.encoder.encode(dto.password()));
        this.service.addUser(UserDto.convertToDto(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(jwtUtil.generateToken(new CustomUserDetails(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogin dto) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                dto.email(),
                dto.password()
            )
        );

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(token);
    }
}
