package com.bcrypt.service;

import java.util.List;
import java.util.Optional;

import com.bcrypt.entity.User;

public interface UserService {
	
	//Add User
	User addUser(User user);
	
	//Update User
	User updateUser(User user);
	
	//Delete User
	void deleteUser(Long id);
	
	//get user by id;
	Optional<User> getUserById(Long id);
	
	//Get User By username
	Optional<User> getUserByUsername(String username);
	
	//get User by email
	Optional<User> getUserByEmail(String email);
	
	//get User by account no
	Optional<User> getUserByAccountNo(Long accountNo);
	
	//get user by addhar
	Optional<User> getUserByAdharNo(Long adhar);
	
	//get user list by Branch
	List<User> getUserByBranch(String branch);
	
	//get all user list
	List<User> getAllUsers();
	
	// is user exist by username
	boolean existsUserByUsername(String username);
	
	// is user exist by email
	boolean existsUserByEmail(String email);
	
	// is user exist by adhar
	boolean existsUserByAadhar(Long adhar);
	
}
