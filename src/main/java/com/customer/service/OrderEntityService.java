package com.customer.service;

import com.customer.entity.Cart;
import com.customer.entity.Customer;
import com.customer.entity.OrderEntity;
import com.customer.entity.ProductQuantity;
import com.customer.repository.CustomerRepository;
import com.customer.repository.OrderEntityRepository;
import com.customer.serviceinterface.OrderEntityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
@Service
public class OrderEntityService implements OrderEntityServiceInterface {

    @Autowired
    private OrderEntityRepository orderEntityRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<OrderEntity> getAll(){
        return orderEntityRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> placeOrder(int cartId){

        OrderEntity orderEntity = new OrderEntity();
        System.out.println("order entity" +orderEntity.toString());
        orderEntity.setDate(new Date().toString());

        List<Cart> list = new ArrayList<>();
        System.out.println("...."+cartService.getById(cartId));
        list.add(cartService.getById(cartId));
        orderEntity.setMycart(list);
        orderEntity.setCustomerId(cartId);
        orderEntityRepository.save(orderEntity);
        System.out.println(orderEntity.toString());


        Customer customer = customerService.getDetails(cartId);
        List<OrderEntity> newList = customer.getCustomerOrder();

        System.out.println("my customer order list...."+newList);
        newList.add(orderEntity);
        customer.setCustomerOrder(newList);
        System.out.println("my Order entity order list...."+orderEntity);
        System.out.println("After  my customer order list...."+newList);

        customerRepository.save(customer);

        return new ResponseEntity<>("Order placed Successfully...", HttpStatus.OK);
    }
}
