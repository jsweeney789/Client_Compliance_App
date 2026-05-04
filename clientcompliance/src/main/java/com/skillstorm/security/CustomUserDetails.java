package com.skillstorm.security;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.skillstorm.models.User;
import com.skillstorm.repositories.UserRepository;

public class CustomUserDetails implements UserDetails {
	
	
	User user;

	
	public CustomUserDetails(User user)
	{
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of(new SimpleGrantedAuthority(user.getRole().toString()));
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}
	
	public User getUser()
	{
		return this.user;
	}

}
