package com.customer.service;

import com.customer.entity.Customer;
import com.customer.exception.user.UserNotFoundException;
import com.customer.repository.CustomerRepository;
import com.customer.serviceinterface.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInterface {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<Object> register(Customer customer){
        customerRepository.save(customer);
        return new ResponseEntity<>("Register Successfully...", HttpStatus.OK);
    }

    @Override
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    @Override
    public Customer getDetails(int id)
    {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new UserNotFoundException();
        }
        return customer.get();
    }

    @Override
    public ResponseEntity<Object> update(Customer customer, int id)
    {
        Customer newCustomer = getDetails(id);
        newCustomer.setCustomerName(customer.getCustomerName());
        newCustomer.setCustomerPassword(customer.getCustomerPassword());
        newCustomer.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());
        customerRepository.save(newCustomer);
        return new ResponseEntity<>("Customer details updated Successfully...",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> delete(int id)
    {
        getDetails(id);
        customerRepository.deleteById(id);
        return new ResponseEntity<>("Customer deleted Successfully...", HttpStatus.OK);
    }

}
