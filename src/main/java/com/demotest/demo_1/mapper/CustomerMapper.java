package com.demotest.demo_1.mapper;

import com.demotest.demo_1.dto.CustomerDto;
import com.demotest.demo_1.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(CustomerDto customerDto, Customer customer){
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setMobile_number(customer.getMobile_number());
        return customerDto;
    }

    public static Customer mapToCustomer(Customer customer, CustomerDto customerDto){
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setMobile_number(customerDto.getMobile_number());
        return customer;
    }
}
