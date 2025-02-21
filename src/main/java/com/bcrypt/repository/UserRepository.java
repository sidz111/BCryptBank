package com.bcrypt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bcrypt.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	//It is used to find user by username (It will use for authentication).
	Optional<User> findByUsername(String username);
	
	//It is used to find user by email id.
	Optional<User> findByEmail(String email);
	
	//It is used to find the user by adhar number.
	Optional<User> findByAdhar(Long adhar);
	
	//It is used to check if a user exists with the given username.
	boolean existsByUsername(String username);
	
	//It is used to check if a user exists with the given adhar number.
	boolean existsByAdhar(Long adhar);
	
	//It is used to check if a user exists with the given email id.
	boolean existsByEmail(String email);
	
	//It is used to find user by account number.
	Optional<User> findByAccountNo(Long accountno);
	
	//It is used to find user by branch name.
	Optional<User> findByBranch(String branch);
}
