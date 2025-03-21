package com.bcrypt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcrypt.entity.Account;
import com.bcrypt.repository.AccountRepository;
import com.bcrypt.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public Account saveAccount(Account account) {
		return this.accountRepository.save(account);
	}

	@Override
	public List<Account> getAllAccount() {
		return this.accountRepository.findAll();
	}

	@Override
	public Optional<Account> getAccountByAccountNumber(String accountNumber) {
		Optional<Account> a = accountRepository.findByAccountNumber(accountNumber);
		if(a.isEmpty()) {
			throw new RuntimeException("Account number is not found.");
		}
		else {
			return Optional.ofNullable(a.get());
		}
	}

	@Override
	public List<Account> getAccountsByType(String AccountType) {
		List<Account> list = accountRepository.findByAccountType(AccountType);
		if(list.size() < 1) {
			throw new RuntimeException("Account type is not found.");
		}
		else {
			return list;
		}
	}

	@Override
	public boolean accountExists(String AccountNumber) {
		return accountRepository.existsByAccountNumber(AccountNumber);
	}

	@Override
	public void updateBalanceByAccountNumber(Double balance, String accountNumber) {
		accountRepository.updateBalanceByAccountNumber(balance, accountNumber);
	}

	@Override
	public void deleteAccountByAccountNumber(String accountNumber) {
		accountRepository.deleteByAccountNumber(accountNumber);
	}

}
