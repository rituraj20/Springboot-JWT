package com.jwt.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.model.User;
import com.jwt.service.JwtService;
import com.jwt.service.MyUserDetailsService;
@RestController

public class Controller {
	@Autowired
	MyUserDetailsService userService;
	@Autowired
	JwtService jwtService;
	@Autowired 
	AuthenticationManager authenticationManager;
	@GetMapping("/hello")
	public String greet() {
		return "hello jee";
	}
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return userService.saveUser(user);
		
	}
	@GetMapping("/fetch")
	public List<User> getDetails(){
		return  userService.getDetails();
	}
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getusername(),user.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getusername());
		}
		else {
			return "failure";
		}
	}
}
