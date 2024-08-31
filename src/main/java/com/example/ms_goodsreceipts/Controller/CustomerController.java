package com.example.ms_goodsreceipts.Controller;

import com.example.ms_goodsreceipts.Entity.Customer;
import com.example.ms_goodsreceipts.Repository.CustomerRepository;

import com.example.ms_goodsreceipts.Request.CustomerRequest;
import com.example.ms_goodsreceipts.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Customer")
//@Api(value = "Sample API", tags = "Sample")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addcustomer")
    private Customer AddCustomer(@RequestBody CustomerRequest customer) {
        return customerService.SveCustomer(customer);
    }
    @GetMapping("/getallcustomer")
    private List<Customer> GetAllCustomer() {
        return customerRepository.findAll();
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