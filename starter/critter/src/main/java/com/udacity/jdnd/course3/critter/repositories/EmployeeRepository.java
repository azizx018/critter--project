package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByDaysAvailable(DayOfWeek day);

}
