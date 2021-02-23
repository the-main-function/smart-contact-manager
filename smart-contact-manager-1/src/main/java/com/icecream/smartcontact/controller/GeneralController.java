package com.icecream.smartcontact.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.icecream.smartcontact.entities.User;
import com.icecream.smartcontact.helper.Message;
import com.icecream.smartcontact.services.UserService;
import com.icecream.smartcontact.validation.UserValidator;

@Controller
public class GeneralController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
			
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String homeRedirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("/home");
		return "home";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("title", "Home - Smart Contact");
		return "home";
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String signup(Model model, HttpSession session) {
		model.addAttribute("title", "Register - Smart Contact");
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user")User user,BindingResult result,
								Model model,HttpSession session){
		
		userValidator.validate(user, result);
		
		if(result.hasErrors()) {
			System.out.println(result);
			model.addAttribute("user",user);
			return "signup";
		}
	
		System.out.println(user);
		
		user.setEnabled(true);
		user.setRole("ROLE_USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userService.registerUser(user);
		
		Message msg = new Message();
		msg.setMessage("Welcome to the SMART CONTACT family");
		msg.setBootstrapClass("alert alert-success");
		session.setAttribute("msg", msg);
		
		return "signup";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String loginUser(Model model) {
		model.addAttribute("title", "Login - Smart Contact");
		System.out.println("in login controller");
		return "login";
	}
}
	