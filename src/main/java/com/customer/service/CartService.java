package com.customer.service;

import com.customer.entity.Cart;
import com.customer.entity.ProductQuantity;
import com.customer.exception.Cart.CartNotFoundException;
import com.customer.exception.product.ProductNotFoundException;
import com.customer.repository.CartRepository;
import com.customer.repository.ProductQuantityRepository;
import com.customer.serviceinterface.CartserviceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements CartserviceInterface {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductQuantityService productQuantityService;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<Cart> getAll(){
        return cartRepository.findAll();
    }


    @Override
    public Cart getById(int id){
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isEmpty()) return new Cart();
        return cartRepository.findById(id).get();
    }

    @Override
    public int calcAmount(List<ProductQuantity> list){
        int sum=0;
        System.out.println("Third ... First..............................");
        for(ProductQuantity i : list){
            String url="http://localhost:8080/product/price/"+i.getProductId();
            String temp=restTemplate.getForObject(url, String.class);

            if(temp == null) throw new ProductNotFoundException();

            else
            {
                int price = Integer.parseInt(temp);

                sum += i.getQuantity() * price;
            }
        }
        return sum;
    }


    @Override
    public ResponseEntity<Object> createCart(int userId, int productId)
    {
        System.out.println("Fisrt...................");
        Cart cart = getById(userId);
        System.out.println("Second............................");
        cart.setCartId(userId);
        ProductQuantity productQuantity = productQuantityService.add(userId,productId);
        List<ProductQuantity> produstList = productQuantityService.getByCardId(userId);
        cart.setCartProductQuantityList(produstList);
        System.out.println("Third......................");
        int amount = calcAmount(produstList);
        cart.setOrderEntityId(userId);
        cart.setCartAmount(amount);
        cartRepository.save(cart);
        System.out.println("Fourth..................................");
        return new ResponseEntity<>("Product added into your cart successfully...", HttpStatus.OK);
    }

    @Override
    public Object getCoupons()
    {
        String url="http://localhost:8080/coupon/view";
        Object[] couponsArray = restTemplate.getForObject(url, Object[].class);
        return List.of(couponsArray);
    }

    @Override
    public ResponseEntity<Object> applyCoupon(int cartId, String couponName)
    {
        Optional<Cart> cart =cartRepository.findById(cartId);
        if(cart.isEmpty()){
            throw new CartNotFoundException();
        }
        System.out.println("hello brother");
        String url="http://localhost:8080/access/coupon/condition/"+couponName;
        int conditionAmount = restTemplate.getForObject(url, int.class);
        System.out.println("percentage         ------"+conditionAmount);
        System.out.println("hello brother........");
        if (conditionAmount == -1){
            return new ResponseEntity<>("Sorry! coupon not found...",HttpStatus.NOT_FOUND);
        }
        if(cart.get().getCartAmount() < conditionAmount){
            return new ResponseEntity<>("Sorry! coupon not Applied...",HttpStatus.OK);
        }

        String url1="http://localhost:8080/access/coupon/discount/"+couponName;
        int percent = restTemplate.getForObject(url1, int.class);
        int amount = afterCoupon(cart.get().getCartAmount(),percent);
        cart.get().setCartAmount(amount);
        System.out.println(cart.get().toString());
        cartRepository.save(cart.get());
        return new ResponseEntity<>("Congratulations! coupon Applied...",HttpStatus.NOT_FOUND);
    }

    @Override
    public int afterCoupon(int amount, int percent){
        int discount = (percent*amount)/100;
        return amount-discount;
    }

}
