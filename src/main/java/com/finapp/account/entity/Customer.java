package com.finapp.account.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true, name="mobile_number")
    private String mobileNumber;



}
