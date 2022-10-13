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
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PetRepository petRepository;

    public List<Pet> findAllPets() {
        List<Pet> allPets = petRepository.findAll();
        return allPets;
    }
    public List<Pet> getPetsByOwner(Long customerId) {
        return petRepository.findAllPetsByCustomer_Id(customerId);
//        List<Pet> petsOfOwner = customerRepository.findById(customerId).get().getPets();
//        return petsOfOwner;

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
