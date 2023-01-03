package com.niit.service;

import com.niit.domain.Customer;
import com.niit.exception.CustomerNotFoundException;
import com.niit.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    CustomerRepository customerRepository;
    public  CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository =customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomerData() throws CustomerNotFoundException {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public boolean deleteCustomer(int cusId) throws CustomerNotFoundException {
        boolean result = false;
        if(customerRepository.findById(cusId).isEmpty()){
            throw new CustomerNotFoundException();
        }else{
            customerRepository.deleteById(cusId);
            result = true;
        }
        return result;
    }

    @Override
    public List<Customer> getAllCustomerByCity(String city) throws CustomerNotFoundException {
        if(customerRepository.findAllCustomerFromCity(city).isEmpty()){
            throw  new CustomerNotFoundException();
        }
        return customerRepository.findAllCustomerFromCity(city);
    }

}
