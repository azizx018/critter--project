package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private PetService petService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        List<Long> petIds = scheduleDTO.getPetIds();
        List<Pet> pets = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        if(petIds != null){
            for(Long petId: petIds){
                pets.add(petService.getPetById(petId));
            }
        }
        if(employeeIds != null) {
            for(Long employeeId : employeeIds) {
                employees.add(employeeService.getEmployeeById(employeeId));
            }
        }
        Schedule schedule = convertScheduleDTOtoSchedule(scheduleDTO);
        schedule.setPets(pets);
        schedule.setEmployees(employees);
        Schedule savedSchedule = scheduleService.saveSchedule(schedule);
        return convertScheduleToScheduleDTO(savedSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedules().stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getScheduleForPetId(petId);
        return schedules.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.scheduleByEmployeeId(employeeId).stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.scheduleByCustomerId(customerId).stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }
    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        copyProperties(schedule, scheduleDTO);
        if(schedule.getPets() != null) {
            scheduleDTO.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        }
        if(schedule.getEmployees() != null) {
            scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        }
        return scheduleDTO;
    }


//    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
//        ScheduleDTO scheduleDTO = new ScheduleDTO();
//        copyProperties(schedule, scheduleDTO);
//        return scheduleDTO;
//    }
    private Schedule convertScheduleDTOtoSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        copyProperties(scheduleDTO, schedule);
        return schedule;
    }
}
