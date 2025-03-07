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
		return "login";
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
			// Checking if the user agreed to terms and conditions.
			if(!agreement) {
				throw new Exception("OOP's! You have not agreed the term and conditions 😅");
			}
			
			//Saving the user in the database.
			this.userService.addUser(user);
			
			//Resetting the form for a new user.
			model.addAttribute("user", new User());
			
			//showing message if user is successfully registered.
			session.setAttribute("message", new Message("✔️ You have successfully registerd. Welcome 😄", "alert-success"));
			
			//Redirecting to the registration page.
			return "redirect:/register";
			
		}catch(Exception e) {
			//Handling exceptions
			e.printStackTrace();
			
			//it is used keeping the user’s entered data
			model.addAttribute("user", user);
			
			//showing message if user is not successfully registered, if user not click on the term and conditions.
			session.setAttribute("message", new Message("❌ "+e.getMessage()+" ", "alert-danger"));
			
			//Redirecting back to the registration page
			return "redirect:/register";
		}
		
	}
}
