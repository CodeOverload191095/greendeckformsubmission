package com.greendeck.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*To apply security in our RestAPI by creating encrypting password and making a login page for 
access.*/

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String[] WHITE_LIST_URLS = { "/createaccount" };

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean // authenticate username and password
	AuthenticationProvider authenticationprovider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;

	}

	// to allot urls to admin and user separately.
	// taking control from spring and providing url on our own.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()

				.antMatchers("/hello").hasAuthority("USER").antMatchers("/admin").hasAuthority("ADMIN").anyRequest()
				.authenticated().and().httpBasic();
	}

}
