package com.skillstorm.controllers;

import java.io.IOException;

import org.springframework.http.ResponseCookie;
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

import jakarta.servlet.http.HttpServletResponse;

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
    public ResponseEntity<?> oauthSuccess(@AuthenticationPrincipal OidcUser oidcUser, HttpServletResponse response) throws IOException {

            if (oidcUser == null) {
            // already logged in via JWT → just redirect
            response.sendRedirect("http://localhost:4200/home");
            return ResponseEntity.ok("Login successful with previous token.");
        }

        User user = userRepository.findByEmail(oidcUser.getEmail()).orElseThrow();
        CustomUserDetails userDetails = new CustomUserDetails(user);
        String jwt = jwtUtil.generateToken(userDetails);
        ResponseCookie cookie = ResponseCookie.from("jwt", jwt)
                .httpOnly(true)
                .secure(false) // set false for http
                .path("/")  //need this so the cookie will work with all paths
                .sameSite("Lax")
                .maxAge(3600) // last an hour
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
        response.sendRedirect("http://localhost:4200/home");
        return ResponseEntity.ok("Login successful");
    }
}
