package com.customer.controller;

import com.customer.entity.Cart;
import com.customer.entity.Customer;
import com.customer.service.CartService;
import com.customer.service.CustomerService;
import com.customer.service.OrderEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderEntityService orderEntityService;

    @GetMapping("/{id}")
    public Cart getByid(@PathVariable("id") int id){
        return cartService.getById(id);
    }

    @GetMapping("/add/{userId}/{productId}")
    public ResponseEntity<Object> addProduct(@PathVariable("userId") int userId,
                                             @PathVariable("productId") int productId)
    {
        System.out.println("inside handler");
        Customer customer = customerService.getDetails(userId);
        return cartService.createCart(userId,productId);
    }

    @GetMapping("/{cartId}/placeOrder")
    public ResponseEntity<Object> placeOrder(@PathVariable("cartId") int cartId)
    {
        return orderEntityService.placeOrder(cartId);
    }


    @GetMapping("/view")
    public List<Cart> showCarts(){
        return cartService.getAll();
    }

    @GetMapping("/coupon")
    public Object showCoupons(){
        return cartService.getCoupons();
    }




    @GetMapping("/{cartId}/{couponName}")
    public ResponseEntity<Object> applyCoupon(@PathVariable("cartId") int cartId,
                                              @PathVariable("couponName") String couponName)
    {
        return cartService.applyCoupon(cartId,couponName);
    }

}
