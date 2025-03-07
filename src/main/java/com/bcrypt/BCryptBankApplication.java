package com.bcrypt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bcrypt.entity.User;
import com.bcrypt.repository.UserRepository;

@SpringBootApplication
public class BCryptBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BCryptBankApplication.class, args);
		System.out.println("Welcome To BCrypt Banking..!!");
	}
	
	@Bean
	CommandLineRunner init(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			if(userRepository.findByUsername("admin")==null) {
				User u = new User();
				u.setUsername("admin");
				u.setPassword(passwordEncoder.encode("1234"));
				u.setRole("ADMIN");
				userRepository.save(u);
				System.out.println("Admin Created done");
			}
		};
	}
	
}
