package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dto.AttendanceDto;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotAvailableException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.mapper.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final IMapper mapperDto;

    @Autowired
    public AttendanceController(IAttendanceService attendanceService, IMapper mapperDto) {
        this.attendanceService = attendanceService;
        this.mapperDto = mapperDto;
    }

    @GetMapping(value = "/")
    public List<AttendanceDto> showAttendances(@RequestParam(value = "fr", defaultValue = "0") @PositiveOrZero int firstElement,
                                               @RequestParam(value = "mx", defaultValue = "15") @PositiveOrZero int maxResult) {
        return mapperDto.mapAll(attendanceService.readAll(firstElement, maxResult), AttendanceDto.class);
    }

    @GetMapping(value = "/sorted")
    public List<AttendanceDto> showAttendances(@RequestParam(value = "sr", defaultValue = "default") String parameter,
                                               @RequestParam(value = "fr", defaultValue = "0") @PositiveOrZero int firstElement,
                                               @RequestParam(value = "mx", defaultValue = "15") @PositiveOrZero int maxResult) {
        return mapperDto.mapAll(attendanceService.readAllSorted(parameter, firstElement, maxResult), AttendanceDto.class);
    }

    @GetMapping(value = "/{id}")
    public AttendanceDto showAttendance(@PathVariable(value = "id") @Positive long index) {
        return mapperDto.map(attendanceService.read(index), AttendanceDto.class);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAttendance(@PathVariable(value = "id") @Positive long index) {
        attendanceService.delete(index);
    }

    @PutMapping(value = "/{id}")
    public void updateAttendance(@PathVariable(value = "id") @Positive long index,
                                 @RequestBody @Validated(AttendanceDto.class) AttendanceDto object) {
        if (index != object.getId()) {
            throw new ElementNotAvailableException("Page index: " + index + " not matched object index: " + object.getId());
        }
        attendanceService.update(mapperDto.map(object, Attendance.class));
    }

    @PostMapping(value = "/")
    public void createAttendance(@RequestBody @Validated(AttendanceDto.class) AttendanceDto object) {
        attendanceService.create(mapperDto.map(object, Attendance.class));
    }
}
