package com.springboot.web.demo.userDemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.web.demo.userDemo.service.user.UserServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private final UserServiceImpl userservice;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurityConfig(UserServiceImpl userservice
				,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userservice = userservice;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/api/user/create")
				.permitAll()
			.anyRequest()
			.authenticated().and()
			.formLogin().and()
			.httpBasic();
		
		http.logout()
			.invalidateHttpSession(true);
			

		// disable frameoptions to enable h2 console in browser
		http.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		provider.setUserDetailsService(userservice);;
		return provider;
	}
	
}
