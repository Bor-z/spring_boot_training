package com.demotest.loans.service.impl;

import com.demotest.loans.constant.LoansConstant;
import com.demotest.loans.dto.LoansDto;
import com.demotest.loans.entity.Loans;
import com.demotest.loans.exceptions.LoanAlreadyExistsException;
import com.demotest.loans.exceptions.ResourceNotFoundException;
import com.demotest.loans.mappers.LoansMapper;
import com.demotest.loans.repository.LoansRepository;
import com.demotest.loans.service.LoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements LoansService {

    LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber){
        Optional<Loans> loan = loansRepository.findByMobileNumber(mobileNumber);

        if(loan.isPresent()){
            throw new LoanAlreadyExistsException("Loan already exists with mobile number : "+mobileNumber);
        }
        loansRepository.save(createLoanDetails(mobileNumber));
    }

    private Loans createLoanDetails(String mobileNumber){
        Loans nloan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        nloan.setLoanNumber(Long.toString(randomLoanNumber));
        nloan.setMobileNumber(mobileNumber);
        nloan.setLoan_type(LoansConstant.HOME_LOAN);
        nloan.setTotal_loan((LoansConstant.NEW_LOAN_LIMIT));
        nloan.setAmount_paid(0);
        nloan.setOutstanding_amount(0);
        nloan.setCreated_at(LocalDateTime.now());
        nloan.setCreated_by("bora");
        return nloan;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber){
        Optional<Loans> op_loan = loansRepository.findByMobileNumber(mobileNumber);

        if(op_loan.isEmpty()){
            throw new ResourceNotFoundException("Loan","MObileNumber",mobileNumber);
        }
        return LoansMapper.mapToLoansDto(op_loan.get(), new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto){
        Optional<Loans> op_loan = loansRepository.findByLoanNumber(loansDto.getLoanNumber());
        if(op_loan.isEmpty()){
            throw new ResourceNotFoundException("Loan","LoanNumber",loansDto.getLoanNumber());
        }
        loansRepository.save(LoansMapper.mapToLoans(loansDto, op_loan.get()));
        return true;

    }
}
