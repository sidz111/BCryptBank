package com.bcrypt.service;

import java.util.List;

import com.bcrypt.entity.Transaction;

public interface TransactionService {

	//Save the transaction.
	Transaction saveTransaction(Transaction transaction);
	
	//get all the transaction.
	List<Transaction> showAllTransactions();
	
	//get transaction by id.
	Transaction getTransactionById(Long id);
	
	//get transaction by type.
	List<Transaction> getTransactionByType(String transactionType);
	
	//get transaction by amount.
	List<Transaction> getTransactionByAmount(Double amount);
	
	//get transaction by timestamp.
	List<Transaction> getTransactionByTimestamp(String timestamp);
	
	//delete a transaction by id.
	void deleteTransaction(Long id);
	
}