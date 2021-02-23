package com.icecream.smartcontact.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.icecream.smartcontact.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	public User findByEmail(String email);
}
