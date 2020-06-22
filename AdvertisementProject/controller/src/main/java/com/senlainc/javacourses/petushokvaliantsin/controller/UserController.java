package com.senlainc.javacourses.petushokvaliantsin.controller;


import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserRatingDto;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserRatingService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.user.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final IUserService userService;
    private final IUserRatingService userRatingService;
    private final IAdvertisementService advertisementService;

    public UserController(IUserRatingService userRatingService, IUserService userService, IAdvertisementService advertisementService) {
        this.userService = userService;
        this.userRatingService = userRatingService;
        this.advertisementService = advertisementService;
    }

    @GetMapping(value = "/{id}")
    public UserDto getUser(@PathVariable(name = "id") @Positive Long index) {
        return userService.getUser(index);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> rateUser(@RequestBody @Validated(UserRatingDto.class) UserRatingDto userRatingDto) {
        return new ResponseEntity<>(userRatingService.create(userRatingDto), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/closed")
    public List<AdvertisementDto> getUserClosedAdvertisements(@PathVariable(name = "id") @Positive Long index,
                                                              @RequestParam(name = "page", defaultValue = "1") @Positive int page,
                                                              @RequestParam(name = "number", defaultValue = "15") @Positive int numberElements) {
        return advertisementService.getUserAdvertisements(index, page, numberElements, "DISABLED");
    }
}
