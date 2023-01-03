package com.niit.repository;

import com.niit.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,Integer> {
    //public String findByEmail(String email);
//public Customer findByCustomerName(String customerName);
    @Query("{'address.city':{$in:[?0]}}")
    List<Customer> findAllCustomerFromCity(String city);
}
