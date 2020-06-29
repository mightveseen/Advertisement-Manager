package com.senlainc.javacourses.petushokvaliantsin.controller.user;

import com.senlainc.javacourses.petushokvaliantsin.dto.StateDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
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
import java.util.List;

@RestController
@RequestMapping(path = "moderator")
public class ModeratorController {

    private final IAdvertisementService advertisementService;

    @Autowired
    public ModeratorController(IAdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    /**
     * Moderator advertisement operation [Show all, show by id, change state]
     */
    @GetMapping(value = "/advertisements")
    public List<AdvertisementDto> getAdvertisements(@RequestParam(name = "page", defaultValue = "1") @Positive int page,
                                                    @RequestParam(name = "number", defaultValue = "15") @Positive int numberElements) {
        return advertisementService.getAdvertisements(page, numberElements, "desc", "date", "none",
                "none", 0, 0, EnumState.MODERATION.name());
    }

    @GetMapping(value = "/advertisements/{id}")
    public AdvertisementDto getAdvertisement(@PathVariable(name = "id") @Positive Long index) {
        return advertisementService.getAdvertisementByModerator(index);
    }

    @PutMapping(value = "/advertisements/{id}")
    public ResponseEntity<Boolean> changeState(@PathVariable(name = "id") @Positive Long index,
                                               @RequestBody @Validated(StateDto.Read.class) StateDto state) {
        return new ResponseEntity<>(advertisementService.updateByModerator(index, state), HttpStatus.OK);
    }
}
