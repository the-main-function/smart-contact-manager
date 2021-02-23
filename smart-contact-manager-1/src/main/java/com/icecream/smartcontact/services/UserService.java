package com.icecream.smartcontact.services;

import com.icecream.smartcontact.entities.User;

public interface UserService {
	public void registerUser(User user);
	public User fetchUserByEmail(String email);
}
