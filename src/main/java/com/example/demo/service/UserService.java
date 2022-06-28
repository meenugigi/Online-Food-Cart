package com.example.demo.service;

import com.example.demo.model.ItemInfo;
import com.example.demo.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.dto.*;


public interface UserService extends UserDetailsService{
	
	User save(UserRegistrationDto userRegDto);

	String getUserContact(String userName);
	
	
}
