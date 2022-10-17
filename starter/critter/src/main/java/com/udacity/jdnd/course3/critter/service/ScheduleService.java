package com.udacity.jdnd.course3.critter.service;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    CustomerRepository customerRepository;
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
    public List<Schedule> scheduleByCustomerId(Long customerId) {
        List<Schedule> scheduleByCustomer = scheduleRepository.getDetailsByCustomer(customerRepository.getOne(customerId));
        return scheduleByCustomer;
    }
    public Schedule saveSchedule(Schedule schedule) {

        return scheduleRepository.save(schedule);
    }
}
