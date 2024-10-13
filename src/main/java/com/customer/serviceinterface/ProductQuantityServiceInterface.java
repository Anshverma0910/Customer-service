package com.customer.serviceinterface;

import com.customer.entity.ProductQuantity;

import java.util.List;

public interface ProductQuantityServiceInterface {

    public List<ProductQuantity> getAll();

    public ProductQuantity add(int cartId,int productId);

    public List<ProductQuantity> getByCardId(int cardId);
}
