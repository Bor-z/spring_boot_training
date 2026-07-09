package com.demotest.loans.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter@ToString@AllArgsConstructor
@NoArgsConstructor
public class Loans extends BasicEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "loan_number")
    private String loanNumber;

    private String loan_type;

    @Column(updatable = false)
    private int total_loan;

    private int amount_paid;

    private int outstanding_amount;
}
