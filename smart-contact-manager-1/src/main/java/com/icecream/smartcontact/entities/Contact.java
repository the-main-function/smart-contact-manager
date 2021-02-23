package com.icecream.smartcontact.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Contact {

	@Id
	@GeneratedValue
	private int contactId;
	
	private String name;
	
	private String nickname;
	
	private String work;
	
	private String email;
	
	private String imageUrl;
	
	private String phoneNo;
	
	@ManyToOne
	private User user;

	public Contact() {
	}
	
	public Contact(int contactId, String name, String nickname, String work, String email, String imageUrl, String phoneNo, User user) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.nickname = nickname;
		this.work = work;
		this.email = email;
		this.imageUrl = imageUrl;
		this.phoneNo = phoneNo;
		this.user = user;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Contact --> " + contactId + " " + name + " " + nickname + " " + work
				+ " "+ email + " " + imageUrl + " " + phoneNo + " ";
	}
}
