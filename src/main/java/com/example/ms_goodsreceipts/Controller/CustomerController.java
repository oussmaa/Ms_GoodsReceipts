package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.Customer;
import com.example.ms_goodsreceipts.Exception.EntityNotFoundExceptions;
import com.example.ms_goodsreceipts.Exception.ResourceNotFoundException;
import com.example.ms_goodsreceipts.Repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/addcustomer")
    private Customer AddCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }
    @GetMapping("/getallcustomer")
    private List<Customer> GetAllCustomer() {
        return customerRepository.findAll().stream().toList();
    }
    @GetMapping("/{id}")
    public Optional<Customer> getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new EntityNotFoundException("Customer not found with ID: " + id);
        } else {
            return customer;
        }
    }


}