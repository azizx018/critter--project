package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }
    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        copyProperties(schedule, scheduleDTO);
        return scheduleDTO;
    }
    private Schedule convertScheduleDTOtoSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        copyProperties(scheduleDTO, schedule);
        return schedule;
    }
}
