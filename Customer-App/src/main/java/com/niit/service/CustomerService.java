package com.niit.service;

import com.niit.domain.Customer;
import com.niit.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomerData() throws CustomerNotFoundException;

    public boolean deleteCustomer(int cusId) throws CustomerNotFoundException;

    List<Customer> getAllCustomerByCity(String city) throws CustomerNotFoundException;


}
