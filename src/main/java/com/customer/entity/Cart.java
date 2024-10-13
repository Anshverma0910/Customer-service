package com.customer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {

    @Id
    private int cartId;

    @OneToMany(mappedBy = "cartId",cascade = CascadeType.ALL)
    private List<ProductQuantity> cartProductQuantityList;

    private int orderEntityId;

    private int cartAmount;

}
