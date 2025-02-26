package com.bcrypt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bcrypt.entity.User;
import com.bcrypt.repository.UserRepository;
import com.bcrypt.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	public UserRepository userRepository;
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Override
	public User addUser(User user) {
		return this.userRepository.save(user);
	}
	
	@Override
	public User updateUser(User user) {
		Optional<User> u = this.userRepository.findById(user.getId());
		if(u.isPresent()) {
			return this.userRepository.save(user);
		}else {
			throw new RuntimeException("User is not found with ID : "+user.getId());
		}
	}
	
	@Override
	public void deleteUser(Long id) {
		Optional<User> u = this.userRepository.findById(id);
		if(u.isPresent()) {
			this.userRepository.deleteById(id);
		}else {
			throw new RuntimeException("User is not found with ID : "+id);
		}
	}
	
	@Override
	public Optional<User> getUserById(Long id) {
		Optional<User> u = this.userRepository.findById(id);
		if(u.isPresent()) {
			return Optional.ofNullable(u.get());
		}else {
			throw new RuntimeException("User is not found with ID : "+id);
		}
	}
	
	@Override
	public Optional<User> getUserByUsername(String username) {
		Optional<User> u = this.userRepository.findByUsername(username);
		if(u.isPresent()) {
			return Optional.ofNullable(u.get());
		}else {
			throw new RuntimeException("User is not found with Username : "+username);
		}
	}
	
	@Override
	public Optional<User> getUserByEmail(String email) {
		Optional<User> u = this.userRepository.findByEmail(email);
		if(u.isPresent()) {
			return Optional.ofNullable(u.get());
		}else {
			throw new RuntimeException("User is not found with Email : "+email);
		}
	}
	
	@Override
	public Optional<User> getUserByAccountNo(Long accountNo) {
		Optional<User> u = this.userRepository.findByAccountNo(accountNo);
		if(u.isPresent()) {
			return Optional.ofNullable(u.get());
		}else {
			throw new RuntimeException("User is not found with Account No : "+accountNo);
		}
	}
	
	@Override
	public Optional<User> getUserByAdharNo(Long adhar) {
		Optional<User> u = this.userRepository.findByAdhar(adhar);
		if(u.isPresent()) {
			return Optional.ofNullable(u.get());
		}else {
			throw new RuntimeException("User is not found with Aadhar No : "+adhar);
		}
	}
	
	@Override
	public List<User> getUserByBranch(String branch) {
		return this.userRepository.findByBranch(branch);
	}
	
	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}
	
	@Override
	public boolean existsUserByUsername(String username) {
		return this.userRepository.existsByUsername(username);
	}
	
	@Override
	public boolean existsUserByEmail(String email) {
		return this.userRepository.existsByEmail(email);
	}
	
	@Override
	public boolean existsUserByAadhar(Long adhar) {
		return this.userRepository.existsByAdhar(adhar);
	}
	
}