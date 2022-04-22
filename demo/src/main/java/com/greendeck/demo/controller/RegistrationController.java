package com.greendeck.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greendeck.demo.entity.User;
import com.greendeck.demo.event.RegistrationCompleteEvent;
import com.greendeck.demo.model.UserModel;
import com.greendeck.demo.service.UserService;

@RestController
public class RegistrationController {
      @Autowired
	private UserService userService;
      
      @Autowired
      private ApplicationEventPublisher publisher;
/* FOR REGISTRATION AND EMAIL VERIFICATION OF THE USER.*/
		@PostMapping("/createaccount")
	public String registerUser(@RequestBody UserModel userModel,final HttpServletRequest request) {
		
		User user=userService.registerUser(userModel);
		
		publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));
		return"success";
	}
	
	
	/*FOR VALIDATING THE REGISTRATION TOKEN GENETRATED*/
	@GetMapping("/verifyRegistration")
	public String verifyRegistration(@RequestParam("token")String token)
	{
	String result=userService.validateVerificationToken(token);
	if(result.equalsIgnoreCase("valid"))
	{
		return "user verified successfully";
		
	}
	else 
		return"not a verified user";
	}
	
	
	private String applicationUrl(HttpServletRequest request) {
		return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		
	}
}
