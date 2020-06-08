package com.senlainc.javacourses.petushokvaliantsin.controller;


import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.IDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final IUserService userService;
    private final IDtoMapper dtoMapper;
    private final IAdvertisementService advertisementService;

    public UserController(IDtoMapper dtoMapper, IUserService userService, IAdvertisementService advertisementService) {
        this.dtoMapper = dtoMapper;
        this.userService = userService;
        this.advertisementService = advertisementService;
    }

    @GetMapping(value = "/{id}")
    public UserDto getUser(@PathVariable(name = "id") @Positive Long index) {
        return dtoMapper.map(userService.read(index), UserDto.class);
    }

    @GetMapping(value = "/{id}/closed")
    public List<AdvertisementDto> getUserAdvertisements(@PathVariable(name = "id") @Positive Long index,
                                                        @RequestParam(name = "page", defaultValue = "1") @Positive int page,
                                                        @RequestParam(name = "number", defaultValue = "15") @Positive int numberElements) {
        return advertisementService.getUserAdvertisements(index, page, numberElements);
    }
}
