package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
