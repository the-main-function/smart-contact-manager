package com.icecream.smartcontact.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.icecream.smartcontact.entities.Contact;
import com.icecream.smartcontact.entities.User;
import com.icecream.smartcontact.helper.Message;
import com.icecream.smartcontact.services.ContactService;
import com.icecream.smartcontact.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value="/dashboard",method = RequestMethod.GET)
	public String userDashboard(Model model, Principal principal ) {
		String email = principal.getName();
		User user = userService.fetchUserByEmail(email);
		model.addAttribute("name", user.getName());
		model.addAttribute("title",user.getName().toUpperCase()+"'s dashboard");
		System.out.println("logged in user --> " +user);
		model.addAttribute("user", user);
		return "user_views/user_dashboard";
	}
	
	@RequestMapping(value="/addContactForm",method = RequestMethod.GET)
	public String getContactAddForm(Model model, Principal principal) {
		model.addAttribute("title", "Add contact - Smart Contact");
		return "user_views/add_contact_form";
	}
	
	@RequestMapping(value="/addContact",method = RequestMethod.POST)
	public String addContact(Contact contact, Principal principal, HttpSession session) {
		System.out.println("contact add handler");
		String email = principal.getName();
		User user = userService.fetchUserByEmail(email);
		System.out.println(user);
		System.out.println(contact);
		contact.setUser(user);
		contactService.addContact(contact);
		Message msg = new Message();
		msg.setMessage("Contact added successfully!!");
		session.setAttribute("msg", msg);;
		System.out.println(contact);
		List<Contact> contactList = user.getContactList();
		System.out.println(contactList);
		return "user_views/add_contact_form";
	}
	
	@RequestMapping(value="/myContacts/{pageId}",method=RequestMethod.GET)
	public String getContacts(@PathVariable("pageId")int pageId, Principal principal, Model model) {
		String email = principal.getName();
		User user = userService.fetchUserByEmail(email);
		
/*		List<Contact> contactList = user.getContactList();
		System.out.println(contactList);
		model.addAttribute("contactList",contactList);
*/
		// above method is not recommended if pagintation is to be done
		
		Pageable pageInfo = PageRequest.of(pageId, 5);
		Page<Contact> contactList = contactService.fetchContacts(user.getUserId(),pageInfo);
		
		System.out.println("myContacts controller :: userId : "+user.getUserId());
		
		model.addAttribute("contactList",contactList);
		model.addAttribute("title",user.getName()+"'s contacts - Smart Contact");
		model.addAttribute("currentPage",pageId);
		model.addAttribute("totalPages", contactList.getTotalPages());
		return "user_views/contact_list";
	}
	
	@RequestMapping(value="/delete/{contactId}",method=RequestMethod.GET)
	public String deleteContact(@PathVariable("contactId")int contactId,Principal principal, Model model) {
		contactService.deleteContact(contactId);
		getContacts(0, principal, model);
		return "redirect:/user/myContacts/0";
	}

	@RequestMapping(value ="/update/{contactId}",method=RequestMethod.GET)
	public String updateContactForm(@PathVariable("contactId")int contactId, Model model) {
		Contact contact = contactService.getContact(contactId);
		model.addAttribute("contact", contact);
		model.addAttribute("title","Update Contact - Smart Contact");
		return "user_views/update_contact_form";
	}
	
	@RequestMapping(value="/updateContact",method = RequestMethod.POST)
	public String updateContact(Contact contact,Principal principal, Model model) {
		System.out.println("update contact handler\n"+contact);
		getContacts(0, principal, model);
		String email = principal.getName();
		User user = userService.fetchUserByEmail(email);
		contact.setUser(user);
		contactService.addContact(contact);
		return "redirect:/user/myContacts/0";
	}
}
