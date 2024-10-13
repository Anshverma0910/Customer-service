package com.customer.service;

import com.customer.entity.ProductQuantity;
import com.customer.repository.ProductQuantityRepository;
import com.customer.serviceinterface.ProductQuantityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQuantityService implements ProductQuantityServiceInterface {

    @Autowired
    private ProductQuantityRepository productQuantityRepository;

    public List<ProductQuantity> getAll(){
        return productQuantityRepository.findAll();
    }

    public ProductQuantity add(int cartId,int productId){
        ProductQuantity productQuantity = productQuantityRepository.
                findByCartIdAndProductId(cartId,productId);

        System.out.println("Second ... First..............................");
        if(productQuantity == null){
            productQuantity = new ProductQuantity();
            System.out.println("Second ... Second ..............................");
            productQuantity.setProductId(productId);
            productQuantity.setQuantity(1);
            productQuantity.setCartId(cartId);
        }
        else{
            productQuantity.setQuantity(productQuantity.getQuantity()+1);
        }
        System.out.println("Second ... Third ..............................");
        productQuantityRepository.save(productQuantity);
        return productQuantity;
    }

    public List<ProductQuantity> getByCardId(int cardId){
        return productQuantityRepository.findByCartId(cardId);
    }



}
