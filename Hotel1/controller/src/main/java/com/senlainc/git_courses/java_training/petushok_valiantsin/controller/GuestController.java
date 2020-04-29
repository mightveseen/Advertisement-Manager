package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dto.GuestDto;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dto.RoomDto;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
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
@RequestMapping(path = "guests")
public class GuestController {

    private final IGuestService guestService;
    private final IOrderService orderService;
    private final IMapper mapperDto;

    @Autowired
    public GuestController(IGuestService guestService, IOrderService orderService, IMapper mapperDto) {
        this.guestService = guestService;
        this.orderService = orderService;
        this.mapperDto = mapperDto;
    }

    @GetMapping
    public List<GuestDto> showGuests(@RequestParam(value = "fe", defaultValue = "0") @PositiveOrZero int firstElement,
                                     @RequestParam(value = "mr", defaultValue = "15") @PositiveOrZero int maxResult) {
        return mapperDto.mapAll(guestService.readAll(firstElement, maxResult), GuestDto.class);
    }

    @GetMapping(value = "/{id}")
    public GuestDto showGuest(@PathVariable(value = "id") @Positive long index) {
        return mapperDto.map(guestService.read(index), GuestDto.class);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGuest(@PathVariable(value = "id") @Positive long index) {
        guestService.delete(index);
    }

    @GetMapping(value = "/{id}/last-rooms")
    public List<RoomDto> showLastRooms(@PathVariable(value = "id") @Positive long index,
                                       @RequestParam(value = "lm", defaultValue = "3") @Positive int limit) {
        return mapperDto.mapAll(orderService.getGuestRooms(index, limit), RoomDto.class);
    }

    @PutMapping(value = "/{id}")
    public void updateGuest(@PathVariable(value = "id") @Positive long index,
                            @RequestBody @Validated(GuestDto.class) GuestDto object) {
        if (index != object.getId()) {
            throw new ElementNotAvailableException("Page index: " + index + " not matched object index: " + object.getId());
        }
        guestService.update(mapperDto.map(object, Guest.class));
    }

    @PostMapping
    public void createGuest(@RequestBody @Validated(GuestDto.class) GuestDto object) {
        guestService.create(mapperDto.map(object, Guest.class));
    }
}
