package com.demotest.accounts.service.impl;

import com.demotest.accounts.constants.AccountsConstants;
import com.demotest.accounts.dto.AccountsDto;
import com.demotest.accounts.dto.CustomerDto;
import com.demotest.accounts.entity.Accounts;
import com.demotest.accounts.entity.Customer;
import com.demotest.accounts.exception.CustomerAlreadyExistsException;
import com.demotest.accounts.exception.ResourceNotFoundException;
import com.demotest.accounts.mapper.AccountsMapper;
import com.demotest.accounts.mapper.CustomerMapper;
import com.demotest.accounts.repository.AccountsRepository;
import com.demotest.accounts.repository.CustomerRepository;
import com.demotest.accounts.service.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

        Customer s_customer = customerRepository.save(customer);
        accountsRepository.save(createAccountDetails(s_customer));
    }

    private Accounts createAccountDetails(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccount_number(randomAccNumber);
        newAccount.setAccount_type(AccountsConstants.SAVINGS);
        newAccount.setBranch_address(AccountsConstants.ADDRESS);

        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber){
        Customer cus = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts acc = accountsRepository.findByCustomerId(cus.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "customerId", cus.getCustomerId().toString())
        );

        AccountsDto accDto = AccountsMapper.mapToAccountsDto(acc, new AccountsDto());

        CustomerDto cusDto = CustomerMapper.mapToCustomerDto(new CustomerDto(),cus);
        cusDto.setAccountsDto(accDto);

        return cusDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto){
        boolean succes = false;
        AccountsDto accDto = customerDto.getAccountsDto();
        if(accDto != null){
            Accounts acc = accountsRepository.findById(accDto.getAccount_number()).orElseThrow(
                    ()-> new ResourceNotFoundException("Account", "accountNumber", accDto.getAccount_number().toString())
            );
            AccountsMapper.mapToAccounts(accDto,acc);
            acc = accountsRepository.save(acc);

            Long cusId = acc.getCustomerId();
            System.out.println("test cusId : "+cusId.toString());
            Customer cus = customerRepository.findById(cusId).orElseThrow(
                    ()-> new ResourceNotFoundException("Customer", "customerId", cusId.toString())
            );

            CustomerMapper.mapToCustomer(cus,customerDto);
            customerRepository.save(cus);
            succes = true;
        }
        return succes;
    }

    @Override
    public boolean deleteAccount(String mobileNumber){

        Customer cus = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(cus.getCustomerId());
        customerRepository.deleteById(cus.getCustomerId());

        return true;
    }
}
