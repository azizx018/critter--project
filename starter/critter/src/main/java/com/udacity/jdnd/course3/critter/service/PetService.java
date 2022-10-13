package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        List<Pet> petsOfOwner = customerRepository.findById(customerId).get().getPets();
        return petsOfOwner;

    }
    public void savePet(Pet pet) {
        petRepository.save(pet);

    }
}
