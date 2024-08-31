package com.example.ms_goodsreceipts.service;

import com.example.ms_goodsreceipts.Entity.Customer;
import com.example.ms_goodsreceipts.Repository.CustomerRepository;
import com.example.ms_goodsreceipts.Request.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Customer SveCustomer(CustomerRequest customerRequest)
    {
        Customer customer = new Customer();
         customer.setCity(customerRequest.getCity());
        customer.setCountry(customerRequest.getCountry());
        customer.setName(customerRequest.getName());
        customer.setAddress(customerRequest.getAddress());
        customer.setEmail(customerRequest.getEmail());
        customer.setState(customerRequest.getState());
        customer.setZip(customerRequest.getZip());
        customer.setPhone(customerRequest.getPhone());
        customer.setAllCost(0);


       return customerRepository.save(customer);
    }
}
