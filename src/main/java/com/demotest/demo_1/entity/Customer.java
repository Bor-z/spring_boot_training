package com.demotest.demo_1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
public class Customer extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;

    private String name;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;
}
