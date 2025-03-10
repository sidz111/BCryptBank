package com.bcrypt.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bcrypt.entity.ContactUs;
import com.bcrypt.entity.Subscribers;
import com.bcrypt.entity.User;
import com.bcrypt.helper.Message;
import com.bcrypt.repository.UserRepository;
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
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String homePage(Model model, HttpSession session) {
		Message message = (Message) session.getAttribute("message");
		if(message!=null) {
			model.addAttribute("message", message);
			session.removeAttribute("message");
		}
		model.addAttribute("title", "BCrypt: Home");
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("user", userRepository.findByUsername(userName));
		return "index.html";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("title", "BCrypt: Login");
		return "login";
	}
	
	@GetMapping("/register-user")
	public String registerPage(Model model) {
		model.addAttribute("title", "BCrypt: Register");
		return "register";
	}
	
	@PostMapping("/register-user-new")
	public String registerNewUser(
			@RequestParam("name") String name,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("email") String email,
			@RequestParam("address") String address,
			@RequestParam("branch") String branch,
			@RequestParam("adhar") Long adhar,
			@RequestParam("dateOfBirth") String dateOfBirth,
			@RequestParam("profilePhoto") MultipartFile photo,
			Model model
			) throws IOException {
		User u = new User();
		u.setName(name);
		u.setUsername(username);
		u.setPassword(passwordEncoder.encode(password));
		u.setEmail(email);
		u.setAddress(address);
		u.setBranch(branch);
		u.setAdhar(adhar);
		u.setDateOfBirth(dateOfBirth);
		u.setRole("ROLE_USER");
		
		String userImage = "default.jpg";
		
		Random r = new Random();
		
		Long n = 1_000_000_000L + ((Long) (r.nextLong() * (9_000_000_000L)));
		
		u.setAccountNo(n);
		if(!photo.isEmpty()) {
			userImage = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
			Path uploadDir = Paths.get("src/main/resources/static/users");
			
			if(!Files.exists(uploadDir)) {
				Files.createDirectories(uploadDir);
			}
			
			Path filePath = uploadDir.resolve(userImage);
			Files.copy(photo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			System.err.println("Path is: "+filePath);
		}
		
		u.setProfilePhoto(userImage);
		
		userService.addUser(u);

		return "redirect:/";
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
	
}
