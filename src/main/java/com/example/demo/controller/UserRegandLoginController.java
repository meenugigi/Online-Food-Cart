package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.service.UserService;

@Controller
public class UserRegandLoginController {

	@Autowired
	private UserService uService;

	public UserRegandLoginController(UserService uService) {
		super();
		this.uService = uService;
	}
	
	@GetMapping("/registration")
	public ModelAndView showRegistrationForm() {
		ModelAndView mView = new ModelAndView("registration");
		mView.addObject("User", new UserRegistrationDto());
		return mView;
	}
	
	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("User") UserRegistrationDto userRegDto) {
		uService.save(userRegDto);
		return "redirect:/registration?success";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	

	
}
