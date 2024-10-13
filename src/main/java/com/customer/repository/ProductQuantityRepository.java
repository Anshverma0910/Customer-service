package com.customer.repository;

import com.customer.entity.ProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductQuantityRepository extends JpaRepository<ProductQuantity,Integer> {

    public ProductQuantity findByProductId(int productId);

    public List<ProductQuantity> findByCartId(int cartId);

    public ProductQuantity findByCartIdAndProductId(int cartId, int productId);
}
