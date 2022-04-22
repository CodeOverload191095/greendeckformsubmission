package com.greendeck.demo.event;

import org.springframework.context.ApplicationEvent;

import com.greendeck.demo.entity.User;

import lombok.Getter;
import lombok.Setter;

//CREATING EVENT FOR REGISTRATION CONFIRMATION
public class RegistrationCompleteEvent extends ApplicationEvent{
private User user;
	private String applicationUrl;
	public RegistrationCompleteEvent(User user,String applicationUrl) {
		
		super(user);
		this.user=user;
		this.applicationUrl=applicationUrl;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getApplicationUrl() {
		return applicationUrl;
	}
	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

}