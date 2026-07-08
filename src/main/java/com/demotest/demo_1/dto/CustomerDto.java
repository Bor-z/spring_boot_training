package com.demotest.demo_1.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private String name;

    private String email;

    private String mobileNumber;

    private AccountsDto accountsDto;
}
