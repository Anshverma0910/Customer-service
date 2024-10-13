package com.customer.serviceinterface;

import com.customer.entity.Cart;
import com.customer.entity.ProductQuantity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartserviceInterface {

    public List<Cart> getAll();

    public Cart getById(int id);

    public int calcAmount(List<ProductQuantity> list);

    public ResponseEntity<Object> createCart(int userId, int productId);

    public Object getCoupons();

    public ResponseEntity<Object> applyCoupon(int cartId, String couponName);

    public int afterCoupon(int amount, int percent);

}
