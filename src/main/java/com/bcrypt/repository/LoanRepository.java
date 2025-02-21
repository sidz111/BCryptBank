package com.bcrypt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bcrypt.entity.Loan;

@Repository
public interface LoanRepository  extends JpaRepository<Loan, Long>{
	
	// To find loan by amount
	List<Loan> findByLoanAmount(Double loan);			
	
	// To find Interest of loan
	List<Loan> findByInterestRate(Double interestRate);
	
	// To find start date of loan
	List<Loan> findByStartDate(String startDate);
	
	// To find end date of loan
	List<Loan> findByEndDate(String endDate);
	
	// To find loan status
	List<Loan> findByLoanStatus(String loanStatus);
	
	
	

}
