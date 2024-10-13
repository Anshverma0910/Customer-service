package com.customer.controller;

import com.customer.entity.OrderEntity;
import com.customer.service.OrderEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orderEntity")
public class OrderEntityContoller {

    @Autowired
    private OrderEntityService orderEntityService;

    @GetMapping("/view")
    public List<OrderEntity> getAll(){
        return orderEntityService.getAll();
    }
}
