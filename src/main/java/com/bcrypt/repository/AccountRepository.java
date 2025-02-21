package com.bcrypt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bcrypt.entity.Account;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	//find account by its account number.
	Optional<Account> findByAccountNumber(String accountNumber);
	
	//find all account type like savings, deposit.
	List<Account> findByAccountType(String accountType);
	
	//account exists or not by its account number 
	boolean existsByAccountNumber(String accountNumber);
	
	//update the balance by account number.
	//JPQL --> Java Persistence Query Language
	@Transactional
	@Modifying
	@Query("UPDATE Account a SET a.balance = :balance WHERE a.accountNumber = :accountNumber")
	void updateBalanceByAccountNumber(Double balance, String accountNumber);
	
	//delete the account by account number.
	@Transactional
	void deleteByAccountNumber(String accountNumber);

}