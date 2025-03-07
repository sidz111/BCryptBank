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
import com.bcrypt.entity.User;
import com.bcrypt.helper.Message;
import com.bcrypt.service.ContactUsService;
import com.bcrypt.service.SubscribersService;
import com.bcrypt.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private ContactUsService contactUsService;
	
	@Autowired
	private SubscribersService subscribersService;
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/")
	public String homePage(Model model, HttpSession session) {
		Message message = (Message) session.getAttribute("message");
		if(message!=null) {
			model.addAttribute("message", message);
			session.removeAttribute("message");
		}
		model.addAttribute("title", "BCrypt: Home");
		return "index.html";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("title", "BCrypt: Login");
		return "login.html";
	}
	
	@GetMapping("/register")
	public String registerPage(Model model, HttpSession session) {
		Message message = (Message) session.getAttribute("message");
	    if (message != null) {
	        model.addAttribute("message", message);
	        session.removeAttribute("message");
	    }
		model.addAttribute("title", "BCrypt: Register");
		return "register";
	}
	
	@GetMapping("/contact")
	public String contactPage(Model model, HttpSession session) {
		Message message = (Message) session.getAttribute("message");
		if(message!=null) {
			model.addAttribute("message", message);
			session.removeAttribute("message");
		}
		model.addAttribute("title", "BCrypt: Contact");
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
	
	@PostMapping("/do_register")
	public String addRegister(@ModelAttribute User user, @RequestParam(value="agreement", defaultValue="false") boolean agreement, Model model, HttpSession session) {
		
		try {	
			if(!agreement) {
				throw new Exception("OOP's! You have not agreed the term and conditions 😅");
			}
			User user1 = new User();
			userService.addUser(user1);
			
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("✔️ Now you are the part of our website. Welcome 😄", "alert-success"));
			
			return "redirect:/register";
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("❌ "+e.getMessage()+" ", "alert-danger"));
			return "redirect:/register";
		}
		
	}
}
