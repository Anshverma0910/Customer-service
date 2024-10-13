package com.customer.serviceinterface;

import com.customer.entity.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerServiceInterface {

    public ResponseEntity<Object> register(Customer customer);

    public List<Customer> getAll();

    public Customer getDetails(int id);

    public ResponseEntity<Object> update(Customer customer, int id);

    public ResponseEntity<Object> delete(int id);
}
