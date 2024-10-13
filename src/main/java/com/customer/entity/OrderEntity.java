package com.customer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int orderEntityId;

    private int customerId;

    @OneToMany(mappedBy = "orderEntityId")
    private List<Cart> mycart;

    private String date;

}