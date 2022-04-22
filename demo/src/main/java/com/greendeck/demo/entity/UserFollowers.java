package com.greendeck.demo.entity;

//FOR KNOWING FOLLOWERS AND FOLLOWING OF AN ACCOUNT.
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import lombok.Data;
@Data
@Entity
public class UserFollowers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	private long userId;
	
	
	private long followerId;
	

}
