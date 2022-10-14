package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    public Employee getEmployeeById(Long employeeId) {
        Optional<Employee> selectedEmployee = employeeRepository.findById(employeeId);
        return selectedEmployee.orElse(null);
    }
    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.get().setDaysAvailable(daysAvailable);
        employeeRepository.save(employee.get());
    }
    public List<Employee> getAvailableEmployees(DayOfWeek dayOfWeek, Set<EmployeeSkill> skills) {
        List<Employee> allEmployeesAvailable = employeeRepository.findAllByDaysAvailable(dayOfWeek);
        List<Employee> filteredBySkillEmployees = new ArrayList<>();
        for (Employee employee:allEmployeesAvailable) {
            if (employee.getSkills().containsAll(skills)){
                filteredBySkillEmployees.add(employee);
            }
        }
        return filteredBySkillEmployees;
    }
}
