package com.demotest.loans.mappers;

import com.demotest.loans.dto.LoansDto;
import com.demotest.loans.entity.Loans;

public class LoansMapper {

    public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setLoan_type(loans.getLoan_type());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setTotal_loan(loans.getTotal_loan());
        loansDto.setAmount_paid(loans.getAmount_paid());
        loansDto.setOutstanding_amount(loans.getOutstanding_amount());
        return loansDto;
    }

    public static Loans mapToLoans(LoansDto loansDto, Loans loans){
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoan_type(loansDto.getLoan_type());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setTotal_loan(loansDto.getTotal_loan());
        loans.setAmount_paid(loansDto.getAmount_paid());
        loans.setOutstanding_amount(loansDto.getOutstanding_amount());
        return loans;
    }

}
