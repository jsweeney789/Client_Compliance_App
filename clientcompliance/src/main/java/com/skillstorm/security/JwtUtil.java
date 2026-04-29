package com.skillstorm.security;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtUtil {
	
	 private final String SECRET = "pKqkNNcZXjxEMzdywR8lnCVj4qNbTguDpQ03uYPOkdj";

	    public String generateToken(CustomUserDetails user) {
	        return JWT.create()
	                .withSubject(user.getUsername())
	                .withIssuedAt(new Date())
	                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
	                .sign(Algorithm.HMAC256(SECRET));
	    }

	    public String extractUsername(String token) {
	        return JWT.require(Algorithm.HMAC256(SECRET))
	                .build()
	                .verify(token)
	                .getSubject();
	    }
	    
	    private boolean isTokenExpired(DecodedJWT token) {
	        return token.getExpiresAt().before(new Date());
	    }
	    
	    public boolean isValid(String token, String username) {
	        try {
	            DecodedJWT decoded = JWT.require(Algorithm.HMAC256(SECRET))
	                .build()
	                .verify(token);
	     
	            return decoded.getSubject().equals(username)
	    && !isTokenExpired(decoded);
	     
	        } catch (Exception e) {
	            return false;
	        }
	    }

}
