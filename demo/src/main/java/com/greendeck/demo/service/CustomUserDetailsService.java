package com.greendeck.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greendeck.demo.entity.User;
import com.greendeck.demo.repository.UserRepository;


	@Service
	public class CustomUserDetailsService implements UserDetailsService {

	    @Autowired
	    private UserRepository userRepository;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    	try{System.out.println("input"+username); //TO CHECK IF CODE WORKS
	        User user = userRepository.findByUsername(username);
	        
	        System.out.println("output"+user); //TO SEE IF IT REACHES THE END 
	        	
	        
	        if(user ==null) {
	            throw new UsernameNotFoundException("User Not Found");
	        }
	        return new CustomUserDetails(user);}catch(Exception e) {
	        	e.printStackTrace();
	        	return null;
	        }
	    }
	}
