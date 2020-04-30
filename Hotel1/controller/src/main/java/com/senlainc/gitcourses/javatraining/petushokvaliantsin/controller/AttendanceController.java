package com.senlainc.gitcourses.javatraining.petushokvaliantsin.controller;

import com.senlainc.gitcourses.javatraining.petushokvaliantsin.api.service.IAttendanceService;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.dto.AttendanceDto;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.model.Attendance;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.exception.NoMatchException;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.mapper.IMapper;
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

    @GetMapping
    public List<AttendanceDto> showAttendances(@RequestParam(value = "fe", defaultValue = "0") @PositiveOrZero int firstElement,
                                               @RequestParam(value = "mr", defaultValue = "15") @PositiveOrZero int maxResult) {
        return mapperDto.mapAll(attendanceService.readAll(firstElement, maxResult), AttendanceDto.class);
    }

    @GetMapping(value = "/sorted-attendances")
    public List<AttendanceDto> showAttendances(@RequestParam(value = "sr", defaultValue = "default") String sort,
                                               @RequestParam(value = "fe", defaultValue = "0") @PositiveOrZero int firstElement,
                                               @RequestParam(value = "mr", defaultValue = "15") @PositiveOrZero int maxResult) {
        return mapperDto.mapAll(attendanceService.readAllSorted(sort, firstElement, maxResult), AttendanceDto.class);
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
            throw new NoMatchException("Page index [" + index + "] not matched object index: [" + object.getId() + "].");
        }
        attendanceService.update(mapperDto.map(object, Attendance.class));
    }

    @PostMapping
    public void createAttendance(@RequestBody @Validated(AttendanceDto.class) AttendanceDto object) {
        attendanceService.create(mapperDto.map(object, Attendance.class));
    }
}
