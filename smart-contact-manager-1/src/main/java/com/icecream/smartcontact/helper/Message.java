package com.icecream.smartcontact.helper;

import org.springframework.stereotype.Component;

public class Message {
	
	private String message;
	private String bootstrapClass;
	
	public Message() {
	}

	public Message(String message, String bootstrapClass) {
		this.message = message;
		this.bootstrapClass = bootstrapClass;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBootstrapClass() {
		return bootstrapClass;
	}

	public void setBootstrapClass(String bootstrapClass) {
		this.bootstrapClass = bootstrapClass;
	}

	@Override
	public String toString() {
		return "Message [message=" + message + ", bootstrapClass=" + bootstrapClass + "]";
	}
}
