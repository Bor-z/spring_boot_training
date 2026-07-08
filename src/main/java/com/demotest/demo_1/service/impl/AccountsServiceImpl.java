package com.demotest.demo_1.service.impl;

import com.demotest.demo_1.constants.AccountsConstants;
import com.demotest.demo_1.dto.CustomerDto;
import com.demotest.demo_1.entity.Accounts;
import com.demotest.demo_1.entity.Customer;
import com.demotest.demo_1.exception.CustomerAlreadyExistsException;
import com.demotest.demo_1.mapper.CustomerMapper;
import com.demotest.demo_1.repository.AccountsRepository;
import com.demotest.demo_1.repository.CustomerRepository;
import com.demotest.demo_1.service.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(new Customer(), customerDto);
        Optional<Customer> op_customer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if(op_customer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exists :( with mobile number: "+customer.getMobileNumber());
        }

        customer.setCreated_at(LocalDateTime.now());
        customer.setCreated_by("test user");
        Customer s_customer = customerRepository.save(customer);
        accountsRepository.save(createAccountDetails(s_customer));
    }

    private Accounts createAccountDetails(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomer_id(customer.getCustomer_id());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccount_number(randomAccNumber);
        newAccount.setAccount_type(AccountsConstants.SAVINGS);
        newAccount.setBranch_address(AccountsConstants.ADDRESS);

        newAccount.setCreated_by("test user");
        newAccount.setCreated_at(LocalDateTime.now());
        return newAccount;
    }
}
