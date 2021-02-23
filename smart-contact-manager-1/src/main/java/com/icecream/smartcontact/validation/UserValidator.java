package com.icecream.smartcontact.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.icecream.smartcontact.entities.User;
import com.icecream.smartcontact.services.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.getClass().equals(User.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","ER_NAME","name cannot be blank");
		
		User user = (User)target;
		User userExist = userService.fetchUserByEmail(user.getEmail());
		if(userExist!=null) {
			errors.rejectValue("email","ER_401","email is already registered");
		}
	}	

}
