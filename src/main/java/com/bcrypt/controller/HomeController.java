package com.bcrypt.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bcrypt.entity.ContactUs;
import com.bcrypt.entity.Subscribers;
import com.bcrypt.helper.Message;
import com.bcrypt.service.ContactUsService;
import com.bcrypt.service.SubscribersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private ContactUsService contactUsService;
	
	@Autowired
	private SubscribersService subscribersService;

	@GetMapping("/")
	public String homePage(Model model, HttpSession session) {
		Message message = (Message) session.getAttribute("message");
		if(message!=null) {
			model.addAttribute("message", message);
			session.removeAttribute("message");
		}
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
	public String contactPage(Model model, HttpSession session) {
		Message message = (Message) session.getAttribute("message");
		if(message!=null) {
			model.addAttribute("message", message);
			session.removeAttribute("message");
		}
		model.addAttribute("contact", new ContactUs());
		return "contact";
	}
	
	@PostMapping("/subscribe-us")
	public String subscribePage(@RequestParam String email, HttpSession session) {
		Subscribers s = new Subscribers();
		s.setEmail(email);
		subscribersService.addSubscriber(s);
		session.setAttribute("message", new Message("✔️ Thankyou for subscribing BCrypt 😄", "alert-success"));
		return "redirect:/";
	}
	
	@PostMapping("/add-contact-us")
	public String addContactUs(@ModelAttribute ContactUs contactUs, Model model, HttpSession session) {
		contactUs.setDateAndTime(new Date().toString());
		contactUsService.addContactUs(contactUs);
		session.setAttribute("message", new Message("✔️ Thankyou for contacting BCrypt, We will connect you soon. 😄", "alert-success"));
		return "redirect:/contact";
	}
	
}
