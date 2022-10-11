package com.udacity.jdnd.course3.critter.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nationalized
    private String name;

    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<Pet> pets;
}
