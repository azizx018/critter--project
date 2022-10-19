package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    private CustomerRepository customerRepository;

    private  PetRepository petRepository;
    public PetService(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public List<Pet> findAllPets() {
        List<Pet> allPets = petRepository.findAll();
        return allPets;
    }
    public List<Pet> getPetsByOwner(Long customerId) {
        return petRepository.findAllPetsByCustomer_Id(customerId);


    }
    public Pet getPetById(Long petId) {
        Optional<Pet> selectedPet = petRepository.findById(petId);
        return selectedPet.orElse(null);
    }
    public Pet savePet(Pet pet) {
        Pet selectedPet = petRepository.save(pet);
        Customer customer = selectedPet.getCustomer();
        customer.insertPet(selectedPet);
        customerRepository.save(customer);
        return selectedPet;

    }
}
