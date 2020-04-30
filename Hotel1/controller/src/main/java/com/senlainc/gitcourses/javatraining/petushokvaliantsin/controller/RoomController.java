package com.senlainc.gitcourses.javatraining.petushokvaliantsin.controller;

import com.senlainc.gitcourses.javatraining.petushokvaliantsin.api.service.IOrderService;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.api.service.IRoomService;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.dto.RoomDto;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.exceptionhandler.AppExceptionHandler;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.model.Room;
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
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "rooms")
public class RoomController extends AppExceptionHandler {

    private final IRoomService roomService;
    private final IOrderService orderService;
    private final IMapper mapper;

    @Autowired
    public RoomController(IRoomService roomService, IOrderService orderService, IMapper mapper) {
        this.roomService = roomService;
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<RoomDto> showRooms(@RequestParam(value = "dp", defaultValue = "all") String display,
                                   @RequestParam(value = "fe", defaultValue = "0") @PositiveOrZero int firstElement,
                                   @RequestParam(value = "mr", defaultValue = "15") @PositiveOrZero int maxResult) {
        return mapper.mapAll(roomService.readAll(display, firstElement, maxResult), RoomDto.class);
    }

    @GetMapping(value = "/sorted-rooms")
    public List<RoomDto> showRooms(@RequestParam(value = "dp", defaultValue = "all") String display,
                                   @RequestParam(value = "sr", defaultValue = "default") String sort,
                                   @RequestParam(value = "fe", defaultValue = "0") @PositiveOrZero int firstElement,
                                   @RequestParam(value = "mr", defaultValue = "15") @PositiveOrZero int maxResult) {
        return mapper.mapAll(roomService.readAllSorted(display, firstElement, maxResult, sort), RoomDto.class);
    }

    @GetMapping(value = "/{id}")
    public RoomDto showRoom(@PathVariable(value = "id") @Positive long index) {
        return mapper.map(roomService.read(index), RoomDto.class);
    }

    @GetMapping(value = "/num-free")
    public Long showNumFree() {
        return roomService.getNumFree();
    }

    @GetMapping(value = "/after-date")
    public List<RoomDto> showAfterDate(@RequestParam("ld") LocalDate date,
                                       @RequestParam(value = "fr", defaultValue = "0") @PositiveOrZero int firstElement,
                                       @RequestParam(value = "mx", defaultValue = "15") @PositiveOrZero int maxResult) {
        return mapper.mapAll(orderService.getRoomsAfterDate(date, firstElement, maxResult), RoomDto.class);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRoom(@PathVariable(value = "id") @Positive long index) {
        roomService.delete(index);
    }

    @PutMapping(value = "/{id}")
    public void updateRoom(@PathVariable(value = "id") @Positive long index,
                           @RequestBody @Validated(RoomDto.class) RoomDto object) {
        if (index != object.getId()) {
            throw new NoMatchException("Page index [" + index + "] not matched object index [" + object.getId() + "].");
        }
        roomService.update(mapper.map(object, Room.class));
    }

    @PostMapping
    public void createRoom(@RequestBody @Validated(RoomDto.class) RoomDto object) {
        roomService.create(mapper.map(object, Room.class));
    }
}
