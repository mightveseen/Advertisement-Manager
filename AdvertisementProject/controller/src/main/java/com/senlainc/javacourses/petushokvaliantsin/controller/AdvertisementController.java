package com.senlainc.javacourses.petushokvaliantsin.controller;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.IDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(path = "advertisements")
public class AdvertisementController {

    private final IAdvertisementService advertisementService;
    private final IDtoMapper dtoMapper;

    @Autowired
    public AdvertisementController(IAdvertisementService advertisementService, IDtoMapper dtoMapper) {
        this.advertisementService = advertisementService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    public List<AdvertisementDto> getAdvertisements(@RequestParam(name = "first", defaultValue = "0") int firstElement,
                                                    @RequestParam(name = "max", defaultValue = "15") int maxResult,
                                                    @RequestParam(name = "direction", defaultValue = "asc") String direction,
                                                    @RequestParam(name = "sort", defaultValue = "default") String sort) {
        if (sort.equals("default")) {
            return dtoMapper.mapAll(advertisementService.readAll(firstElement, maxResult, direction), AdvertisementDto.class);
        }
        return dtoMapper.mapAll(advertisementService.readAll(firstElement, maxResult, direction, sort), AdvertisementDto.class);
    }

    @GetMapping(value = "/{id}")
    public AdvertisementDto getAdvertisement(@PathVariable(name = "id") @Positive Long index) {
        return dtoMapper.map(advertisementService.read(index), AdvertisementDto.class);
    }
}
