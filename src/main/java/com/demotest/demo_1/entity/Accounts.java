package com.demotest.demo_1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
public class Accounts extends BasicEntity {

    private Long customer_id;

    @Id
    private Long account_number;

    private String account_type;

    private String branch_address;
}
