package com.customer.exception.Cart;

import com.customer.exception.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CartNotFoundController {
    @ExceptionHandler(value = CartNotFoundException.class)
    public ResponseEntity<Object> exception(CartNotFoundException exception) {
        return new ResponseEntity<>("Sorry! Cart not found", HttpStatus.NOT_FOUND);
    }
}
