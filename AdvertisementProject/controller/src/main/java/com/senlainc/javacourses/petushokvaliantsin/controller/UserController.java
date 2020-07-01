package com.senlainc.javacourses.petushokvaliantsin.controller;


import com.senlainc.javacourses.petushokvaliantsin.dto.ResultListDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserRatingDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
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
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") @Positive Long index) {
        return new ResponseEntity<>(userService.getUser(index), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Boolean> rateUser(@RequestBody @Validated({UserRatingDto.Create.class, UserDto.Read.class}) UserRatingDto userRatingDto) {
        return new ResponseEntity<>(userRatingService.create(userRatingDto), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/closed")
    public ResultListDto<AdvertisementDto> getUserClosedAdvertisements(@PathVariable(name = "id") @Positive Long index,
                                                                       @RequestParam(name = "page", defaultValue = "1") @Positive int page,
                                                                       @RequestParam(name = "number", defaultValue = "15") @Positive int numberElements) {
        return new ResultListDto<>(advertisementService.getSize(EnumState.DISABLED),
                advertisementService.getUserAdvertisements(index, page, numberElements, EnumState.DISABLED));
    }
}
