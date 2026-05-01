package com.skillstorm.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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
import com.skillstorm.security.CustomUserDetails;
import com.skillstorm.security.JwtUtil;
import com.skillstorm.services.UserService;

import jakarta.servlet.http.HttpServletResponse;


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
    public ResponseEntity<String> register(@RequestBody UserDto dto, HttpServletResponse response) {
        User user = new User(dto.email(), this.encoder.encode(dto.password()));
        user = this.service.addUser(UserDto.convertToDto(user));
        System.out.println(user);
        
        CustomUserDetails userDetails = new CustomUserDetails(user);
        String token = jwtUtil.generateToken(userDetails);
        
        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(false) // set false for http
                .path("/")  //need this so the cookie will work with all paths
                .sameSite("Lax")
                .maxAge(3600) // last an hour
                .build();

        	response.addHeader("Set-Cookie", cookie.toString());
        
        return ResponseEntity.ok("Created New User");
    }

    @PostMapping("/")
    public ResponseEntity<String> login(@RequestBody UserLogin dto,HttpServletResponse response) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                dto.email(),
                dto.password()
            )
        );

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        String token = jwtUtil.generateToken(userDetails);
        
        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(false) // set false for http
                .path("/")  //need this so the cookie will work with all paths
                .sameSite("Lax")
                .maxAge(3600) // last an hour
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok("Login successful");
        
        
        //return ResponseEntity.ok(token);
    }

}
