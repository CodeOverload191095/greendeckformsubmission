package com.greendeck.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.greendeck.demo.entity.VerificationToken;
/*FOR FINDING THE REGISTRATION VERIFICATION TOKEN IN DATABSE AND MATCH IT WITH THE ONE RECEIVED ON 
MAIL */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	public VerificationToken findByToken(String token);

}
