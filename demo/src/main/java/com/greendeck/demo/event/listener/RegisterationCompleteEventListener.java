package com.greendeck.demo.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.greendeck.demo.entity.User;
import com.greendeck.demo.event.RegistrationCompleteEvent;
import com.greendeck.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;
//CREATING EVENT LISTENER FOR EVENT THAT HAS BEEN PUBLISHED.
@Component
@Slf4j
public class RegisterationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

	@Autowired
	private UserService userService;
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		
		//create verification for the user via link
		User user=event.getUser();
		String token= UUID.randomUUID().toString();
		userService.saveVerificationTokenForUser(token,user);
		
		
		//send mail to user
		String url= event.getApplicationUrl()+"/verifyRegistration?token="+token;
		
		//send verification emailmethod()
		log.info("click the link to verify account:{}",url);
	}
	

}
