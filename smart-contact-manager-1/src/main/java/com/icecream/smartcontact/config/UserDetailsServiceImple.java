package com.icecream.smartcontact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.icecream.smartcontact.entities.User;
import com.icecream.smartcontact.services.UserService;

@Service
public class UserDetailsServiceImple implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.fetchUserByEmail(email);
		if(user==null) {
			throw new UsernameNotFoundException("no such user!!");
		}
		CustomUserDetails customUserDetails = new CustomUserDetails(user);
		return customUserDetails;
	}

}
