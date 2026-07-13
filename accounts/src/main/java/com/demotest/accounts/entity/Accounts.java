package com.demotest.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
public class Accounts extends BasicEntity {

    @Column(name = "customer_id")
    private Long customerId;

    @Id
    private Long account_number;

    private String account_type;

    private String branch_address;
}
