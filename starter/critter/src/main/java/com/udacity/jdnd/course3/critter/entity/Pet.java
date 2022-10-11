package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.entity.Customer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    private String name;

    private LocalDateTime birthDate;

    @Column(length = 800)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Customer customer;


}
