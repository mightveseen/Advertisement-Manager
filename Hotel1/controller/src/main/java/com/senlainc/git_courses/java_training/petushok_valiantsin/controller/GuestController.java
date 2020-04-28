package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dto.GuestDto;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.mapper.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(path = "guests")
public class GuestController {

    private final IGuestService guestService;
    private final IMapper mapper;

    @Autowired
    public GuestController(IGuestService guestService, IMapper mapper) {
        this.guestService = guestService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/")
    public List<GuestDto> showGuests() {
        return mapper.mapAll(guestService.getGuests(), GuestDto.class);
    }

    @GetMapping(value = "/{id}")
    public GuestDto showGuest(@PathVariable(value = "id") @Positive long index) {
        return mapper.map(guestService.getGuest(index), GuestDto.class);
    }

    @DeleteMapping(value = "/{id}/delete")
    public void deleteGuest(@PathVariable(value = "id") @Positive long index) {
        guestService.delete(index);
    }
}
