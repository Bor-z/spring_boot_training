package com.demotest.loans.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LoansDto {

    @NotEmpty(message = "MobileNumber can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "MobileNumber must be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "LoanNumber can not be a null or empty")
    private String loanNumber;

    @NotEmpty(message = "Loan type can not be a null or empty")
    private String loan_type;

    @PositiveOrZero(message = "Total loan can't be negative.")
    private int total_loan;

    @PositiveOrZero(message = "Amount paid can't be negative.")
    private int amount_paid;

    @PositiveOrZero(message = "Outstanding amount can't be negative.")
    private int outstanding_amount;
}
