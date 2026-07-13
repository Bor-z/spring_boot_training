package com.demotest.accounts.service;

import com.demotest.accounts.dto.CustomerDto;

public interface AccountsService {
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
