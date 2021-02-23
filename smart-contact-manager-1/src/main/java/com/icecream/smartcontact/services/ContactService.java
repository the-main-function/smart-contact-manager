package com.icecream.smartcontact.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.icecream.smartcontact.entities.Contact;

@Service
public interface ContactService {
	public void addContact(Contact contact);
	public Page<Contact> fetchContacts(int userId,Pageable pageable);
	public void deleteContact(int contactId);
	public Contact getContact(int contactId);
}
