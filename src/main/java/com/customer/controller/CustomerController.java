package com.customer.controller;

import com.customer.entity.Customer;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/view")
    public List<Customer> getAll(){
        return customerService.getAll();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Customer customer)
    {
        return customerService.register(customer);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<Object> update(@RequestBody Customer customer,
                                         @PathVariable("id") int id)
    {
        return customerService.update(customer,id);
    }

    @GetMapping("/{id}")
    public Customer getDetails(@PathVariable("id") int id)
    {
        return customerService.getDetails(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id)
    {
        return customerService.delete(id);
    }

    @GetMapping("/hello")
    public String hello()
    {
        return "welcome buddy";
    }
}
