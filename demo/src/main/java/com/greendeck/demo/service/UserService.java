package com.greendeck.demo.service;

import java.util.List;

import com.greendeck.demo.entity.User;
import com.greendeck.demo.model.UserModel;

public interface UserService {

public 	User registerUser(UserModel userModel);

 void saveVerificationTokenForUser(String token, User user);

public String validateVerificationToken(String token);
	
public List<User>getFollowers(Long userId) ;
}
