package com.udacity.jdnd.course3.critter.service;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ScheduleRepository scheduleRepository;


    public List<Schedule> getAllSchedules() {
        List<Schedule> allSchedules = scheduleRepository.findAll();
        return allSchedules;
    }

    public List<Schedule> getScheduleForPetId(Long petId) {
        List<Schedule> scheduleForSelectedPet = scheduleRepository.getDetailsByPets(petRepository.getOne(petId));
        return scheduleForSelectedPet;
    }
    public List<Schedule> scheduleByEmployeeId(Long employeeId) {
        List<Schedule> scheduleByEmployee = scheduleRepository.getDetailsByEmployees(employeeRepository.getOne(employeeId));
        return scheduleByEmployee;

    }

    public List<Schedule> getScheduleForCustomer(Long customerId) {
        List<Pet> customerPets = petRepository.findAllPetsByCustomer_Id(customerId);
        List<Schedule> schedules= scheduleRepository.findAllByPetsIn(customerPets);
        return schedules.stream().map(this::saveSchedule).collect(Collectors.toList());
    }

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
}
