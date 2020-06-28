package com.springdemo.rest;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {

        Customer customer = customerService.getCustomer(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        return customer;
    }

    @RequestMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer){

        //hibernate gives a new index if the index of the object being sent is empty or equal to 0
        customer.setId(0);
        customerService.saveCustomer(customer);

        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer){

        customerService.saveCustomer(customer);

        return customer;
    }
}
