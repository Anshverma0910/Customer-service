package com.customer.controller;

import com.customer.entity.ProductQuantity;
import com.customer.service.ProductQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quantity")
public class ProductQuantityController {

    @Autowired
    private ProductQuantityService productQuantityService;

    @GetMapping("/all")
    public List<ProductQuantity> getAll(){
        return productQuantityService.getAll();
    }
}
