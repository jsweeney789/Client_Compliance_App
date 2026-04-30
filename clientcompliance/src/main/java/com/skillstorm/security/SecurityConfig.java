package com.skillstorm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	CustomUserDetailsService service;
	JwtFilter jwtAuthFilter;
	
	public SecurityConfig(CustomUserDetailsService service,JwtFilter jwtAuthFilter)
	{
		this.service = service;
		this.jwtAuthFilter = jwtAuthFilter;
	}
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
@Bean
    AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) {
        // start building an auth object
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        // chain onto that object the services we want to use, i.e. UserDetailsService and password encoder
        auth.userDetailsService(service).passwordEncoder(passwordEncoder);
        return auth.build();
    }
 
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)
	{
		http.csrf(csrf-> csrf.disable());
		
		http.authorizeHttpRequests(auth-> auth
		
		.requestMatchers("/api/login/**").permitAll()
		.requestMatchers("/api/login/").permitAll()
		.requestMatchers("/oauth2/**", "/login/oauth2/**").permitAll()
		.requestMatchers("/api/clientrecord/**").hasAuthority("RELATIONSHIP_MANAGER")
		.anyRequest().authenticated()
		)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		//.oauth2Login(Customizer.withDefaults());
		.oauth2Login(oauth -> oauth.loginPage("/oauth2/authorization/google"));
		http.exceptionHandling(ex -> ex
			.defaultAuthenticationEntryPointFor(
				new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
				request -> request.getRequestURI().startsWith("/api/")
			)
		);
				
				
				
				
		
		return http.build();
	}
	
	

}
