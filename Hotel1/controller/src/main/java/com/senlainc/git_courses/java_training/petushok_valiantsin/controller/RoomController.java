package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dto.RoomDto;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
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
@RequestMapping(path = "rooms")
public class RoomController {

    private final IRoomService roomService;
    private final IMapper mapper;

    @Autowired
    public RoomController(IRoomService roomService, IMapper mapper) {
        this.roomService = roomService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/")
    public List<RoomDto> showGuests(@RequestParam(value = "ds", defaultValue = "all") String parameter,
                                    @RequestParam(value = "fr", defaultValue = "0") @PositiveOrZero int firstElement,
                                    @RequestParam(value = "mx", defaultValue = "15") @PositiveOrZero int maxResult) {
        return mapper.mapAll(roomService.readAll(parameter, firstElement, maxResult), RoomDto.class);
    }

    @GetMapping(value = "/sorted-rooms")
    public List<RoomDto> showGuests(@RequestParam(value = "ds", defaultValue = "all") String displayParameter,
                                    @RequestParam(value = "sr", defaultValue = "default") String sortParameter,
                                    @RequestParam(value = "fr", defaultValue = "0") @PositiveOrZero int firstElement,
                                    @RequestParam(value = "mx", defaultValue = "15") @PositiveOrZero int maxResult) {
        return mapper.mapAll(roomService.readAllSorted(displayParameter, firstElement, maxResult, sortParameter), RoomDto.class);
    }

    @GetMapping(value = "/{id}")
    public RoomDto showGuest(@PathVariable(value = "id") @Positive long index) {
        return mapper.map(roomService.read(index), RoomDto.class);
    }

    @GetMapping(value = "/num-free-rooms")
    public Long numFreeRoom() {
        return roomService.getNumFree();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGuest(@PathVariable(value = "id") @Positive long index) {
        roomService.delete(index);
    }

    @PutMapping(value = "/{id}")
    public void updateGuest(@PathVariable(value = "id") @Positive long index,
                            @RequestBody @Validated(RoomDto.class) RoomDto object) {
        roomService.update(mapper.map(object, Room.class));
    }

    @PostMapping(value = "/")
    public void createGuest(@RequestBody @Validated(RoomDto.class) RoomDto object) {
        roomService.create(mapper.map(object, Room.class));
    }
}
