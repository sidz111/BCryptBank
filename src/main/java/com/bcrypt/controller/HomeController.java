package com.bcrypt.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bcrypt.entity.Account;
import com.bcrypt.entity.ContactUs;
import com.bcrypt.entity.Subscribers;
import com.bcrypt.entity.Transaction;
import com.bcrypt.entity.User;
import com.bcrypt.helper.Message;
import com.bcrypt.repository.UserRepository;
import com.bcrypt.service.AccountService;
import com.bcrypt.service.ContactUsService;
import com.bcrypt.service.SubscribersService;
import com.bcrypt.service.TransactionService;
import com.bcrypt.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	JavaMailSender javaMailSender;

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

	@Autowired
	TransactionService transactionService;

	@Autowired
	AccountService accountService;

	@GetMapping("/")
	public String homePage(Model model, HttpSession session) {
		Message message = (Message) session.getAttribute("message");
		if (message != null) {
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
	public String registerNewUser(@RequestParam("name") String name, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("email") String email,
			@RequestParam("address") String address, @RequestParam("branch") String branch,
			@RequestParam("adhar") Long adhar, @RequestParam("dateOfBirth") String dateOfBirth,
			@RequestParam("profilePhoto") MultipartFile photo, Model model) throws IOException, MessagingException {
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
		if (!photo.isEmpty()) {
			userImage = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
			Path uploadDir = Paths.get("src/main/resources/static/users");

			if (!Files.exists(uploadDir)) {
				Files.createDirectories(uploadDir);
			}

			Path filePath = uploadDir.resolve(userImage);
			Files.copy(photo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			System.err.println("Path is: " + filePath);
		}

		u.setProfilePhoto(userImage);

		userService.addUser(u);
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
		helper.setFrom("sssurwade2212@gmail.com", "BCrypt Bank");
		helper.setTo(email);
		helper.setSubject("Welcome to BCrypt Bank");
		
		String emailContent = "<h1>Welcome to Our Bank</h1></br>"
				+ "Welcome "
				+ u.getName()
				+ "<h1>Your Username is: " + u.getEmail()
				+ "<h2> and password is: "+ password
				;
		helper.setText(emailContent, true);
		javaMailSender.send(mailMessage);

		return "redirect:/";
	}

	@GetMapping("/contact")
	public String contactPage(Model model, HttpSession session) {
		Message message = (Message) session.getAttribute("message");
		if (message != null) {
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
		session.setAttribute("message",
				new Message("✔️ Thankyou for contacting BCrypt, We will connect you soon. 😄", "alert-success"));
		return "redirect:/contact";
	}
	
	@GetMapping("/create-acc-page")
	public String getSendMoneyPage() {
		return "create-acc-page";
	}

	@PostMapping("/create-acc")
	public String createAccount(@RequestParam("accountType") String accountType, @RequestParam("username") String username) {
		Optional<User> user = userService.getUserByUsername(username);
		//Add exception here if user null --> Deepesh + Akash
		Account account = new Account();
		account.setUser(user.get());
		account.setAccountNumber(user.get().getAccountNo().toString());
		account.setAccountType(accountType);
		account.setBalance(1000d);
//		Transaction transaction = new Transaction();
//		account.setTransactions(Arrays.asList(transaction));
		accountService.saveAccount(account);
		return "redirect:/";
	}
	
	@GetMapping("/transaction-money")
	public String getTransactionPage() {
		return "transaction-money";
	}

	@PostMapping("/send-money")
	public String sendMoney(@RequestParam("accountNumber") String accountNumber, @RequestParam("balance") Double balance) {
		Optional<Account> account = accountService.getAccountByAccountNumber(accountNumber);
		Double userBalance = account.get().getBalance();
		userBalance += balance;
		account.get().setBalance(userBalance);
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> user = userService.getUserByUsername(userName);
		Optional<Account> userAccount = accountService.getAccountByAccountNumber(user.get().getAccountNo().toString());
		Double b = userAccount.get().getBalance();
		b-=balance;
		userAccount.get().setBalance(b);
		accountService.saveAccount(account.get());
		accountService.saveAccount(userAccount.get());
		return "redirect:/";
	}

}
