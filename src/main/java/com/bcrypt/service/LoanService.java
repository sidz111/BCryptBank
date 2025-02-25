package com.bcrypt.service;

import java.util.List;

import com.bcrypt.entity.Loan;

public interface LoanService {

	Loan saveLoan(Loan loan);
	
	List<Loan> getAllLoans();
	
	void deleteLoanById(Long Id);
	
	Loan updateLoan(Loan loan);
	
	List<Loan> getLoanByLoanAmount(Double loan);
	
	List<Loan> getLoanByInterestRate(Double interestRate);
	
	List<Loan> getLoanByStartDate(String startDate);
	
	List<Loan> getLoanByEndDate(String endDate);
	
	List<Loan> getLoanByLoanStatus(String loanStatus);
}
