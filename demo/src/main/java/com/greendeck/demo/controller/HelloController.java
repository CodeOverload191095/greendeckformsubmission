package com.greendeck.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.greendeck.demo.entity.User;
import com.greendeck.demo.service.UserServiceImpl;



@RestController
public class HelloController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	/*dummy endpoint to check the if direct access given to certain endpoints work ,mentioned
	in WebSecurityConfig.java(inside confi package) */
	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}

	/* For /getfollowers.... TAKING JSON DATA FROM POSTMAN */
	@GetMapping("/getfollowers/{userid}")
	public List<User> getFollowers(@PathVariable ("userid")Long userid  ) {	
	List<User>followerlist= userServiceImpl.getFollowers(userid);
	System.out.println(followerlist);
		return followerlist;
	}
	
	/* FOR /you-may-know/{userid}      TAKING JSON DATA FROM POSTMAN*/
	@GetMapping("/you-may-know/{userid}")
	private List<User> recommendedFollowers(@PathVariable("userid")Long userid) {
			
		List<User>recommendedFollowerlist= userServiceImpl.getRecommnededFollowersUserObject(userid);
		
		System.out.println(recommendedFollowerlist);
			return recommendedFollowerlist;
	}
	
}
