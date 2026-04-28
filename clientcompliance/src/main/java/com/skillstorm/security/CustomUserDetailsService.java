package com.skillstorm.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skillstorm.models.User;
import com.skillstorm.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	UserRepository userrepo;
	CustomUserDetailsService(UserRepository userrepo)
	{
		this.userrepo = userrepo;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user =this.userrepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		return new CustomUserDetails(user);
	}

}
