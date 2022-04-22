package com.greendeck.demo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greendeck.demo.entity.User;
import com.greendeck.demo.entity.UserFollowers;
import com.greendeck.demo.entity.VerificationToken;
import com.greendeck.demo.model.UserModel;
import com.greendeck.demo.repository.UserFollowerRepository;
import com.greendeck.demo.repository.UserRepository;
import com.greendeck.demo.repository.VerificationTokenRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	// @Autowired
	// private PasswordEncoder passwordEncoder;

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Autowired
	private UserFollowerRepository userFollowerRepository;

	@Override
	public User registerUser(UserModel userModel) {

		User user = new User();
		user.setEmail(userModel.getEmail());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setRole("USER");
		

		userRepository.save(user);
		return user;
	}

	@Override
	public void saveVerificationTokenForUser(String token, User user) {
		VerificationToken verificationToken = new VerificationToken(user, token);
		// need repo for this
		verificationTokenRepository.save(verificationToken);
	}

	@Override
	public String validateVerificationToken(String token) {
		VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

		if (verificationToken == null) {

			return "invalid";
		}
		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();

		if ((verificationToken.getExpirationTime().getTime() - cal.getTime().getTime()) <= 0) {
			verificationTokenRepository.delete(verificationToken);
			return "token expired";

		}

		user.setEnabled(true); // enabling user
		userRepository.save(user);
		return "valid token";

	}
//for /getfollowers
	@Override
	public List<User> getFollowers(Long userId) {
		List<Long> followers = userFollowerRepository.getFollowers(userId);
		return userRepository.findAllById(followers);
	}

	public List<User> getRecommnededFollowersUserObject(Long userId) {
		return userRepository.findAllById(getrecommendedFollowers(userId));

	}

	public Set<Long> getrecommendedFollowers(Long userId) {
		List<UserFollowers> userFollowers = userFollowerRepository.findAll();
		List<Long> followers = userFollowers.stream().filter(entry -> entry.getUserId() == userId).map(entry -> {
			return entry.getFollowerId();
		}).collect(Collectors.toList());
		Set<Long> recommendedFollowers = new HashSet<>();
		for (Long followerId : followers) {
			recommendedFollowers.addAll(userFollowers.stream()
					.filter(entry -> (entry.getUserId() == followerId) && (entry.getFollowerId() != userId))
					.map(entry -> {
						return entry.getFollowerId();
					}).collect(Collectors.toSet()));

		}
		return recommendedFollowers;
	}

}
