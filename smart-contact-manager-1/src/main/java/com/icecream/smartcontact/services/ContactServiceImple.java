package com.icecream.smartcontact.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.icecream.smartcontact.dao.ContactRepository;
import com.icecream.smartcontact.entities.Contact;

@Service
public class ContactServiceImple implements ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public void addContact(Contact contact) {
		contactRepository.save(contact);
	}

	@Override
	public Page<Contact> fetchContacts(int userId, Pageable pageable) {
		Page<Contact> contactsList = contactRepository.findContactsByUserId(userId,pageable);
		return contactsList;
	}

	@Override
	public void deleteContact(int contactId) {
		contactRepository.deleteById(contactId);
	}

	@Override
	public Contact getContact(int contactId) {
		Optional<Contact> contactOptional = contactRepository.findById(contactId);
		if(contactOptional.isPresent()) {
			return contactOptional.get();
		}
		else {
			return null;
		}
	}
	
	

}
