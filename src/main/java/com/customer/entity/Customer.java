package com.customer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int customerId;
    private String customerName;
    private String customerPassword;
    private String customerPhoneNumber;

    @OneToMany(mappedBy = "customerId",cascade = CascadeType.ALL)
    private List<OrderEntity> CustomerOrder;

}
