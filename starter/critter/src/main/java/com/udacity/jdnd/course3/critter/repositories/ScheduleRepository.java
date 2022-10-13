package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByEmployeesContaining(Long employeeId);

    List<Schedule> findAllByPetsContaining(Long petId);

    List<Schedule> findAllSchedulesByPet(Pet pet);
}
