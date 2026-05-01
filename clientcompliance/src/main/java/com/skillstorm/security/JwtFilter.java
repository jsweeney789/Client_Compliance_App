package com.skillstorm.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtFilter(JwtUtil jwtUtil, CustomUserDetailsService uds) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = uds;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
    	
    	
    	System.out.println("Filter hit");
//        String header = request.getHeader("Authorization");
//        System.out.println("header: "+ header);
//        if (header != null && header.startsWith("Bearer ")) {
//            String token = header.substring(7);
//            System.out.println("Token: "+ token);
            
    	 String token = null;

    	    // 1. Read JWT from cookie
    	    if (request.getCookies() != null) {
    	        for (Cookie cookie : request.getCookies()) {
    	            if ("jwt".equals(cookie.getName())) {
    	                token = cookie.getValue();
    	                System.out.println("Token from cookie: " + token);
    	                break;
    	            }
    	        }
    	    }
            
            
            
            if (token == null) {
            	
            	 chain.doFilter(request, response);
            	 return;
            }
            
            try {
                String username = jwtUtil.extractUsername(token);
                
                UserDetails user = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                user, null, user.getAuthorities()
                        );
                System.out.println("Create Auth token: " + auth);

                SecurityContextHolder.getContext().setAuthentication(auth);
                
                System.out.println("Security Context: " + SecurityContextHolder.getContext().getAuthentication());
            } catch (Exception e) {
                e.printStackTrace();
            }
        

        chain.doFilter(request, response);
    }
}
