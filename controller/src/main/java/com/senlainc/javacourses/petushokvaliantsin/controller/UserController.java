package com.senlainc.javacourses.petushokvaliantsin.controller;


import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.combination.ResultListDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserRatingDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
import com.senlainc.javacourses.petushokvaliantsin.service.api.advertisement.AdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.user.UserRatingService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.user.UserService;
import lombok.RequiredArgsConstructor;
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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "users")
public class UserController {

    private static final String DEFAULT_STRING = "none";
    private static final Double DEFAULT_PRICE = 0.0;
    private final UserService userService;
    private final UserRatingService userRatingService;
    private final AdvertisementService advertisementService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") @Positive Long index) {
        return new ResponseEntity<>(userService.read(index), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Boolean> rateUser(@PathVariable(name = "id") @Positive Long ratedUserIndex,
                                            @RequestBody @Validated(UserRatingDto.Create.class) UserRatingDto userRatingDto,
                                            @NotNull Principal principal) {
        return new ResponseEntity<>(userRatingService.create(principal.getName(), ratedUserIndex, userRatingDto), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/closed")
    public ResultListDto<AdvertisementDto> getUserClosedAdvertisements(@PathVariable(name = "id") @Positive Long index,
                                                                       @RequestParam(name = "page", defaultValue = "0") @Positive int page,
                                                                       @RequestParam(name = "number", defaultValue = "15") @Positive int numberElements) {
        return advertisementService.readAllWithUser(index, page, numberElements, EnumState.DISABLED);
    }
}
