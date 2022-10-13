package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private CustomerService customerService;
    private PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Customer customer = null;
        if ((Long) petDTO.getOwnerId() != null){
            customer = customerService.getCustomerById(petDTO.getOwnerId());
        }
        Pet pet = convertPetDTOtoPet(petDTO);
        pet.setCustomer(customer);
        Pet savedPet = petService.savePet(pet);
        return convertPetToPetDTO(savedPet);
    }


    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        //include a check that the returned pet is not null
        //if not null conversion is finished otherwise return null
        if( pet != null) {
            return convertPetToPetDTO(pet);
        }
        return null;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.findAllPets().stream().map(this::convertPetToPetDTO).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long customerId) {
        return petService.getPetsByOwner(customerId).stream().map(this::convertPetToPetDTO).collect(Collectors.toList());
    }

    private PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }

    private Pet convertPetDTOtoPet(PetDTO petDTO) {
        Pet pet = new Pet();
        copyProperties(petDTO, pet);
        return pet;
    }
}
