package com.bcrypt.service;

import java.util.List;
import java.util.Optional;

import com.bcrypt.entity.Account;

public interface AccountService {
	
	//save a new account
	Account saveAccount(Account account);
	
	//Get all accounts
	List<Account> getAllAccount();
	
	//get account by account number
	Optional<Account> getAccountByAccountNumber(String accountNumber);
	
	//get account by its type
	List<Account> getAccountsByType(String AccountType);
	
	//check account exists by account number or not.
	boolean accountExists(String AccountNumber);
	
	//update the balance of account number.
	void updateBalanceByAccountNumber(Double balance, String accountNumber);
	
	//delete the balance of account number.
	void deleteAccountByAccountNumber(String accountNumber);
	
}
