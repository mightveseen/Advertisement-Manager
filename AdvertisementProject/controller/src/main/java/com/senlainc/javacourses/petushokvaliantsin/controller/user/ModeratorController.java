package com.senlainc.javacourses.petushokvaliantsin.controller.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.StateDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.combination.ResultListDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.Arrays;

@RestController
@RequestMapping(path = "moderator")
public class ModeratorController {

    private static final String ENUM_EXCEPTION = "State with name [%s] not exist";
    private static final String DEFAULT_STRING = "none";
    private static final Double DEFAULT_PRICE = 0.0;
    private final IAdvertisementService advertisementService;

    @Autowired
    public ModeratorController(IAdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    /**
     * Moderator advertisement operation [Show all, show by id, change state]
     */
    @GetMapping(value = "/advertisements")
    public ResultListDto<AdvertisementDto> getAdvertisements(@RequestParam(name = "page", defaultValue = "1") @Positive int page,
                                                             @RequestParam(name = "number", defaultValue = "15") @Positive int numberElements,
                                                             @RequestParam(name = "direction", defaultValue = "desc") String direction,
                                                             @RequestParam(name = "sort", defaultValue = "date") String sort,
                                                             @RequestParam(name = "state", defaultValue = "moderation") String state) {
        final EnumState parsedState = parseEnumState(state);
        return new ResultListDto<>(advertisementService.readSize(DEFAULT_STRING, DEFAULT_STRING, DEFAULT_PRICE, DEFAULT_PRICE, parsedState),
                advertisementService.readAll(page, numberElements, direction, sort, DEFAULT_STRING, DEFAULT_STRING, DEFAULT_PRICE, DEFAULT_PRICE, parsedState));
    }

    @GetMapping(value = "/advertisements/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisement(@PathVariable(name = "id") @Positive Long index) {
        return new ResponseEntity<>(advertisementService.readByModerator(index), HttpStatus.OK);
    }

    @PutMapping(value = "/advertisements/{id}")
    public ResponseEntity<Boolean> changeState(@PathVariable(name = "id") @Positive Long index,
                                               @RequestBody @Validated(StateDto.Read.class) StateDto state) {
        return new ResponseEntity<>(advertisementService.updateStateByModerator(index, state), HttpStatus.OK);
    }

    private EnumState parseEnumState(String state) {
        if (Arrays.stream(EnumState.values()).noneMatch(i -> i.name().equalsIgnoreCase(state))) {
            throw new EntityNotExistException(String.format(ENUM_EXCEPTION, state));
        }
        return EnumState.valueOf(state.toUpperCase());
    }
}
