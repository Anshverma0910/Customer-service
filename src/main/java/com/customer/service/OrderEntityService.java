package com.customer.service;

import com.customer.entity.Cart;
import com.customer.entity.Customer;
import com.customer.entity.OrderEntity;
import com.customer.entity.ProductQuantity;
import com.customer.repository.CartRepository;
import com.customer.repository.CustomerRepository;
import com.customer.repository.OrderEntityRepository;
import com.customer.serviceinterface.OrderEntityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<OrderEntity> getAll(){
        List<OrderEntity> all = orderEntityRepository.findAll();

        System.out.println("@@@@@@@@@@@@@@@"+all);
        return all;
    }

    @Override
    public ResponseEntity<Object> placeOrder(int cartId){

        OrderEntity orderEntity = new OrderEntity();
        System.out.println("order entity" +orderEntity.toString());
        orderEntity.setDate(new Date().toString());

        List<Cart> list = new ArrayList<>();
        System.out.println("...."+cartService.getById(cartId));
        list.add(cartService.getById(cartId));
        System.out.println(cartService.getById(cartId)+"jddgqwudgqdwqud");
        orderEntity.setMycart(list);
        orderEntity.setCustomerId(cartId);
        System.out.println(orderEntity.getMycart()+"------------------");
        System.out.println("fjcvbk.........."+orderEntity.toString());

        OrderEntity newObject = orderEntityRepository.save(orderEntity);
        System.out.println("hello brother...........................edfveav");
        int index = newObject.getOrderEntityId();
        System.out.println("id is ........................"+index);

        System.out.println("...........before...........");
        Optional<Cart> cart = cartRepository.findById(cartId);
        System.out.println(".............after............");
        System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"+cart.get());
        cart.get().setOrderEntityId(index);
        System.out.println("////////////////////////////////////");

        System.out.println("llllllllllllllllllllllllllllllllll"+cart.get());
        cartRepository.save(cart.get());



        System.out.println(".....................................");
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
