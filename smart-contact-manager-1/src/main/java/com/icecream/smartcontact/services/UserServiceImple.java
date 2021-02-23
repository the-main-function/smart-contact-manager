package com.icecream.smartcontact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icecream.smartcontact.dao.UserRepository;
import com.icecream.smartcontact.entities.User;

@Service
public class UserServiceImple implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void registerUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User fetchUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
