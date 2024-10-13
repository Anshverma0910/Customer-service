package com.customer.serviceinterface;

import com.customer.entity.OrderEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderEntityServiceInterface {

    public List<OrderEntity> getAll();

    public ResponseEntity<Object> placeOrder(int cartId);


}
