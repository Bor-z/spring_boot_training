package com.demotest.demo_1.service;

import com.demotest.demo_1.dto.CustomerDto;

public interface AccountsService {
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);
}
