package com.bcrypt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcrypt.entity.Loan;
import com.bcrypt.repository.LoanRepository;
import com.bcrypt.service.LoanService;

@Service
public class LoanServiceimpl implements LoanService  {
	
	@Autowired
	private LoanRepository loanRepository ;
	
	  public LoanServiceimpl(LoanRepository loanRepository)
	  {
		  super();
		  this.loanRepository = loanRepository;
		  
	  }

	@Override
	public Loan saveLoan(Loan loan) {

		return this.loanRepository.save(loan);
	}

	@Override
	public List<Loan> getAllLoans() {
		return this.loanRepository.findAll();
	}

	@Override
	public void deleteLoanById(Long Id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Loan updateLoan(Loan loan) {
		Optional<Loan> l = this.loanRepository.findById(loan.getId());
		if(l.isPresent()) {
			return this.loanRepository.save(loan);
		}
		else
		{
			throw new RuntimeException("Loan not found with id : "+loan.getId());
		}
		
	}

	@Override
	public List<Loan> getLoanByLoanAmount(Double loan) {
		return this.loanRepository.findByLoanAmount(loan);
	}

	@Override
	public List<Loan> getLoanByInterestRate(Double interestRate) {
		// TODO Auto-generated method stub
		return this.loanRepository.findByInterestRate(interestRate);
	}

	@Override
	public List<Loan> getLoanByStartDate(String startDate) {
		// TODO Auto-generated method stub
		return this.loanRepository.findByStartDate(startDate);
	}

	@Override
	public List<Loan> getLoanByEndDate(String endDate) {
		// TODO Auto-generated method stub
		return this.loanRepository.findByEndDate(endDate);
	}

	@Override
	public List<Loan> getLoanByLoanStatus(String loanStatus) {
		// TODO Auto-generated method stub
		return this.loanRepository.findByLoanStatus(loanStatus);
	}

}
