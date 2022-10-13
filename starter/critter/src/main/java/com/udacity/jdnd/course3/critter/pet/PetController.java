package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
//        Customer customer = null;
//        if ((Long) petDTO.getOwnerId() != null){
//            customer = customerService.findByCu
//        }
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<PetDTO> getPets(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        throw new UnsupportedOperationException();
    }

//    private PetDTO convertPetToPetDTO(Pet pet) {
//        PetDTO petDTO = new PetDTO();
//        copyProperties(pet, petDTO);
//        petDTO.setOwnerId(pet.getCustomer().getId());
//        return petDTO;
//    }

//    private Pet convertPetDTOtoPet(PetDTO petDTO) {
//        Pet pet = new Pet();
//        copyProperties(petDTO, pet);
//        return pet;
//    }
}
