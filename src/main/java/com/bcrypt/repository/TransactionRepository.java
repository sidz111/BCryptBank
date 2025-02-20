package com.bcrypt.repository; // Repository interface is used to create custom methods and  use inbuilt  methods.

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bcrypt.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByTransactionType(String transactionType); // custom method

	List<Transaction> findByAmount(Double amount);// to find by amount

	List<Transaction> findByTimestamp(String timestamp);// to find transactions by time.
}
