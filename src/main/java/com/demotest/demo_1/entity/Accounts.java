package com.demotest.demo_1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
public class Accounts extends BasicEntity {

    public Long customer_id;

    @Id
    public Long account_number;

    public String account_type;

    public String branch_address;
}
