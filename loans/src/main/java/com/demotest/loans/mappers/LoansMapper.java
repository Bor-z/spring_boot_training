package com.demotest.loans.mappers;

import com.demotest.loans.dto.LoansDto;
import com.demotest.loans.entity.Loans;

public class LoansMapper {

    public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {
        loansDto.setLoan_number(loans.getLoan_number());
        loansDto.setLoan_type(loans.getLoan_type());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setTotal_loan(loans.getTotal_loan());
        loansDto.setAmount_paid(loans.getAmount_paid());
        loansDto.setOutstanding_amount(loans.getOutstanding_amount());
        return loansDto;
    }

}
