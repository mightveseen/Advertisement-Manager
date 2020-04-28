package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping(path = "guests")
public class GuestController {

    private final IGuestService guestService;

    @Autowired
    public GuestController(IGuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping(value = "/{id}")
    public String showGuest(@PathVariable(value = "id") @Positive long index) {
        return guestService.getGuest(index).toString();
    }

    @DeleteMapping(value = "/{id}/delete")
    public void deleteGuest(@PathVariable(value = "id") @Positive long index) {
        guestService.delete(index);
    }
}
