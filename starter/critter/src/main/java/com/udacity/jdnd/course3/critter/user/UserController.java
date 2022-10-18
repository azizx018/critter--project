package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PetService petService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        List<Long> petIds = customerDTO.getPetIds();
        List<Pet> pets = new ArrayList<>();
        if(petIds != null) {
            for(Long petId: petIds) {
                pets.add(petService.getPetById(petId));
            }
        }
        Customer customer = convertCustomerDTOToCustomer(customerDTO);
        customer.setPets(pets);
        Customer savedCustomer = customerService.saveCustomer(customer);
        return convertCustomerToCustomerDTO(savedCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.findAllCustomers().stream().map(this::convertCustomerToCustomerDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = customerService.getOwnerByPetId(petId);
        if(customer != null) {
            return convertCustomerToCustomerDTO(customer);
        }
        return null;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        Set<EmployeeSkill> activities = new HashSet<>();

        if(skills != null) {
            activities = new HashSet<EmployeeSkill>(employeeDTO.getSkills());
        }
        Employee employee = convertEmployeeDTOToEmployee(employeeDTO);
        employee.setSkills(activities);

        Employee savedEmployee = employeeService.saveEmployee(employee);

        return convertEmployeeToEmployeeDTO(savedEmployee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if(employee != null) {
            return convertEmployeeToEmployeeDTO(employee);
        }
        return null;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setEmployeeAvailability(daysAvailable, employeeId);

    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employeeList = employeeService.getAvailableEmployees(employeeDTO.getDate().getDayOfWeek(), employeeDTO.getSkills());
        return employeeList.stream().map(this::convertEmployeeToEmployeeDTO).collect(Collectors.toList());
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        copyProperties(customer, customerDTO);
        if(customer.getPets() != null) {
            customerDTO.setPetIds(customer.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        }
        return customerDTO;
    }
    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        copyProperties(customerDTO, customer);
        return customer;
    }

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee pet) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Set<EmployeeSkill> activities;

        if(pet.getActivities() != null) {
            activities = new HashSet<EmployeeSkill>(pet.getActivities());
        } else {
            activities = new HashSet<EmployeeSkill>();
        }
        employeeDTO.setSkills(activities);
        copyProperties(pet, employeeDTO);
        return employeeDTO;
    }
    private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        Set<EmployeeSkill> skills;
        if(employeeDTO.getSkills() != null) {
            skills = new HashSet<EmployeeSkill>(employeeDTO.getSkills());
        } else {
            skills = new HashSet<EmployeeSkill>();
        }
        copyProperties(employeeDTO, employee);
        employee.setSkills(skills);
        return employee;

    }


}
