package com.jwt.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.dao.UserRepository;
import com.jwt.model.User;
import com.jwt.model.UserPrincipal;


@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepo.findByUsername(username);
		if(user==null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("User 404");
		}
		return new UserPrincipal(user);
	}

	public User saveUser(User user) {
		userRepo.save(user);
		return user;
		
	}

	public List<User> getDetails() {
		// TODO Auto-generated method stub
		 return userRepo.findAll();
		
		
	}

}
