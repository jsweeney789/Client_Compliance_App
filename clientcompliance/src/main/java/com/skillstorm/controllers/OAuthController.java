package com.skillstorm.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.models.User;
import com.skillstorm.repositories.UserRepository;
import com.skillstorm.security.CustomUserDetails;
import com.skillstorm.security.JwtUtil;

@RestController
@RequestMapping("/api/login")
public class OAuthController {

    private JwtUtil jwtUtil;
    private UserRepository userRepository;
    public OAuthController(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @GetMapping("/oauth2/success")
    public ResponseEntity<?> oauthSuccess(@AuthenticationPrincipal OidcUser oidcUser) {
        User user = userRepository.findByEmail(oidcUser.getEmail()).orElseThrow();
        CustomUserDetails userDetails = new CustomUserDetails(user);
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwt);

        // need to update this to use cookies and also return frontend redirect in an http servlet response
    }
}
