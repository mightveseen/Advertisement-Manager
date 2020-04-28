package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dto.AttendanceDto;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.mapper.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "attendances")
public class AttendanceController {

    private final IAttendanceService attendanceService;
    private final IMapper mapper;

    @Autowired
    public AttendanceController(IAttendanceService attendanceService, IMapper mapper) {
        this.attendanceService = attendanceService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/")
    public List<AttendanceDto> showGuests(@RequestParam(value = "fr", defaultValue = "0") @PositiveOrZero int firstElement,
                                          @RequestParam(value = "mx", defaultValue = "15") @PositiveOrZero int maxResult) {
        return mapper.mapAll(attendanceService.readAll(firstElement, maxResult), AttendanceDto.class);
    }

    @GetMapping(value = "/{id}")
    public AttendanceDto showGuest(@PathVariable(value = "id") @Positive long index) {
        return mapper.map(attendanceService.read(index), AttendanceDto.class);
    }

    @DeleteMapping(value = "/{id}/delete")
    public void deleteGuest(@PathVariable(value = "id") @Positive long index) {
        attendanceService.delete(index);
    }
}
