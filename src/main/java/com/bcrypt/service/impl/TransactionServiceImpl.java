package com.bcrypt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcrypt.entity.Transaction;
import com.bcrypt.repository.TransactionRepository;
import com.bcrypt.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private TransactionRepository transactionRepository;
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		super();
		this.transactionRepository = transactionRepository;
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		return this.transactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> showAllTransactions() {
		return this.transactionRepository.findAll();
	}

	@Override
	public Transaction getTransactionById(Long id) {
		Optional<Transaction> t = this.transactionRepository.findById(id);
		if(t.isPresent()) {
			return t.get();
		}else {
			throw new RuntimeException("Transaction is not found with ID : "+id);
		}
	}

	@Override
	public List<Transaction> getTransactionByType(String transactionType) {
		return this.transactionRepository.findByTransactionType(transactionType);
	}

	@Override
	public List<Transaction> getTransactionByAmount(Double amount) {
		return this.transactionRepository.findByAmount(amount);
	}

	@Override
	public List<Transaction> getTransactionByTimestamp(String timestamp) {
		return this.transactionRepository.findByTimestamp(timestamp);
	}

	@Override
	public void deleteTransaction(Long id) {
		Optional<Transaction> t = this.transactionRepository.findById(id);
		if(t.isPresent()) {
			this.transactionRepository.findById(id);
		}else {
			throw new RuntimeException("Transaction is not found with ID : "+id);
		}
	}

}
