package com.skillstorm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
	
	private final CustomOAuth2UserService customOAuth2UserService;
    CustomUserDetailsService service;
	JwtFilter jwtAuthFilter;
	
	public SecurityConfig(CustomUserDetailsService service,JwtFilter jwtAuthFilter, CustomOAuth2UserService customOAuth2UserService)
	{
		this.service = service;
		this.jwtAuthFilter = jwtAuthFilter;
        this.customOAuth2UserService = customOAuth2UserService;
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
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		// TODO: bypassing stuff for development, should fix later
		http.csrf(csrf-> csrf.disable())
			.cors(Customizer.withDefaults());
		
		http.authorizeHttpRequests(auth-> auth
		// login endpoints must be permitted to anyone
		.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.requestMatchers("/api/login/**").permitAll()
		.requestMatchers("/api/login/").permitAll()
		.requestMatchers("/oauth2/**", "/login/oauth2/**", "/oauth2/authorization/google").permitAll()
		
		// api endpoints should have some kind of protection
		
		.requestMatchers("/api/clientrecord/**").hasAnyAuthority("RELATIONSHIP_MANAGER", "ADMINISTRATOR")
		.requestMatchers(HttpMethod.PUT, "/api/cases/**").hasAnyAuthority("RELATIONSHIP_MANAGER", "ADMINISTRATOR")
		.requestMatchers("/api/cases/**").hasAnyAuthority("COMPLIANCE_OFFICER", "ADMINISTRATOR")
		.requestMatchers("/oauth2/**", "/login/oauth2/**").permitAll()
		.requestMatchers("/api/clientrecord/**").hasAnyAuthority("RELATIONSHIP_MANAGER","BASIC_USER")
		.anyRequest().authenticated()
		)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.oauth2Login(oauth -> oauth
			.userInfoEndpoint(user -> user.oidcUserService(customOAuth2UserService))
			.defaultSuccessUrl("/api/login/oauth2/success", true)  // your post-login endpoint
			.failureUrl("/login?error=true")
		);
		http.exceptionHandling(ex -> ex
			.defaultAuthenticationEntryPointFor(
				new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
				request -> request.getRequestURI().startsWith("/api/")
			)
		);
		
		return http.build();
	}
	
	

}
