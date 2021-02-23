package com.icecream.smartcontact.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int userId;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@Column(name="img_url")
	private String imageUrl;
	
	private String role;
	
	private boolean enabled;
	
	@Column(length = 200)
	private String about;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Contact> contactList;

	public User() {
	}

	public User(int userId, String name, String email, String password, String imageUrl, String role, boolean enabled, String about, List<Contact> contactList) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.imageUrl = imageUrl;
		this.role = role;
		this.enabled = enabled;
		this.about = about;
		this.contactList = contactList;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	@Override
	public String toString() {
		return "User --> " + userId + " " + name + " " + email + " " + password + " " + imageUrl + " " + role + " "+enabled + " " + about + " " + contactList + "";
	}	
}
