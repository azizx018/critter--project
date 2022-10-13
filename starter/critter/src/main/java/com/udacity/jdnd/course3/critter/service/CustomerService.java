package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PetRepository petRepository;


//    public CustomerService(CustomerRepository customerRepository, PetRepository petRepository) {
//        this.customerRepository = customerRepository;
//        this.petRepository = petRepository;
//    }

    public List<Customer> findAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        return allCustomers;
    }

    public Customer getOwnerByPet(Long petId) {
        Customer selectedCustomer = petRepository.findById(petId).get().getCustomer();
        return selectedCustomer;

    }
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }


}
