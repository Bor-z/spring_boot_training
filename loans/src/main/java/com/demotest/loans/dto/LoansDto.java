package com.demotest.loans.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoansDto {

    @NotEmpty(message = "MobileNumber can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "MobileNumber must be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "LoanNumber can not be a null or empty")
    private String loan_number;

    @NotEmpty(message = "Loan type can not be a null or empty")
    private String loan_type;

    @NotEmpty(message = "Total loan can not be a null or empty")
    @Min(value = 0, message = "Total loan can't be negative.")
    private Long total_loan;

    @Min(value = 0, message = "Amount paid can't be negative.")
    private Long amount_paid;
}
