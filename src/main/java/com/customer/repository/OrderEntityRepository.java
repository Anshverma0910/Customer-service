package com.customer.repository;

import com.customer.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer> {

   public OrderEntity findByCustomerId(int customerId);
}
