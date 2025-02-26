package com.bcrypt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bcrypt.entity.Subscribers;
import com.bcrypt.service.ContactUsService;
import com.bcrypt.service.SubscribersService;

@Controller
public class HomeController {
	
	@Autowired
	private ContactUsService contactUsService;
	
	@Autowired
	private SubscribersService subscribersService;

	@GetMapping("/")
	public String homePage() {
		return "index.html";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login.html";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}
	
	@GetMapping("/contact")
	public String contactPage() {
		return "contact";
	}
	
	@PostMapping("/subscribe-us")
	public String subscribePage(@RequestParam String email) {
		Subscribers s = new Subscribers();
		s.setEmail(email);
		subscribersService.addSubscriber(s);
		return "redirect:/";
	}
	
	
}
