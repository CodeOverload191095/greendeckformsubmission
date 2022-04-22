package com.greendeck.demo.entity;

//FOR REGISTRATION ,VERIFICATION TOKEN GENERATED AFTER REGISTRATION.

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Entity
@Data
public class VerificationToken {
	
	//EXPIRATION TIME
private  static final int EXPIRATION_TIME=10;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private Long id;
	private String token;
	private Date expirationTime;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id",nullable=false,foreignKey= @ForeignKey(name="FK_USER_VERIFY_TOKEN"))
	private User user;
	
	public VerificationToken(User user,String token) {
		
		super();
		this.token=token;
		this.user=user;
		this.expirationTime=calculateExpirationDate(EXPIRATION_TIME);
	}
	
	public VerificationToken(String token) {
		super();
		this.token=token;
		this.expirationTime=calculateExpirationDate(EXPIRATION_TIME);
	}
	private Date calculateExpirationDate(int expirationTime) {
		
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MINUTE,expirationTime);
		return new Date(calendar.getTime().getTime());
	}


}
