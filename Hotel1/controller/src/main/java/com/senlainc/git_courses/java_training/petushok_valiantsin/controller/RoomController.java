package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dto.RoomDto;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.mapper.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
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
    public List<RoomDto> showGuests(@RequestParam(value = "ds", defaultValue = "all") String parameter) {
        return mapper.mapAll(roomService.getRooms(parameter), RoomDto.class);
    }

    @GetMapping(value = "/sorted-rooms")
    public List<RoomDto> showGuests(@RequestParam(value = "ds", defaultValue = "all") String displayParameter,
                                    @RequestParam(value = "sr", defaultValue = "default") String sortParameter) {
        return mapper.mapAll(roomService.getSortedRooms(displayParameter, sortParameter), RoomDto.class);
    }

    @GetMapping(value = "/{id}")
    public RoomDto showGuest(@PathVariable(value = "id") @Positive long index) {
        return mapper.map(roomService.getRoom(index), RoomDto.class);
    }

    @GetMapping(value = "/num-free-rooms")
    public String numFreeRoom() {
        return "Number of free room: " + roomService.getNumFree();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGuest(@PathVariable(value = "id") @Positive long index) {
        roomService.delete(index);
    }
}
