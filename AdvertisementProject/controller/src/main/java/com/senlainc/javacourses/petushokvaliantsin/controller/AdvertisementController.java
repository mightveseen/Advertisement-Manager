package com.senlainc.javacourses.petushokvaliantsin.controller;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCommentDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementCommentService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.IDtoMapper;
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
import java.util.List;

@RestController
@RequestMapping(path = "advertisements")
public class AdvertisementController {

    private final IAdvertisementService advertisementService;
    private final IAdvertisementCommentService advertisementCommentService;
    private final IDtoMapper dtoMapper;

    @Autowired
    public AdvertisementController(IAdvertisementService advertisementService, IAdvertisementCommentService advertisementCommentService,
                                   IDtoMapper dtoMapper) {
        this.advertisementService = advertisementService;
        this.advertisementCommentService = advertisementCommentService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    public List<AdvertisementDto> getAdvertisements(@RequestParam(name = "first", defaultValue = "0") int firstElement,
                                                    @RequestParam(name = "max", defaultValue = "15") int maxResult,
                                                    @RequestParam(name = "direction", defaultValue = "asc") String direction,
                                                    @RequestParam(name = "sort", defaultValue = "default") String sort) {
        return dtoMapper.mapAll(advertisementService.readAll(firstElement, maxResult, direction, sort), AdvertisementDto.class);
    }

    @GetMapping(value = "/{id}")
    public AdvertisementDto getAdvertisement(@PathVariable(name = "id") @Positive Long index) {
        return dtoMapper.map(advertisementService.read(index), AdvertisementDto.class);
    }

    @GetMapping(value = "/{id}/comments")
    public List<AdvertisementCommentDto> getAdvertisementComments(@PathVariable(name = "id") @Positive Long index,
                                                                  @RequestParam(name = "first", defaultValue = "0") int firstElement,
                                                                  @RequestParam(name = "max", defaultValue = "15") int maxResult,
                                                                  @RequestParam(name = "direction", defaultValue = "asc") String direction,
                                                                  @RequestParam(name = "sort", defaultValue = "default") String sort) {
        return dtoMapper.mapAll(advertisementCommentService.readAllComments(index, firstElement, maxResult, direction, sort), AdvertisementCommentDto.class);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteAdvertisement(@PathVariable(name = "id") @Positive Long index) {
        return advertisementService.delete(index);
    }

    @PutMapping(value = "/{id}")
    public boolean updateAdvertisement(@RequestBody @Validated(AdvertisementDto.class) AdvertisementDto object) {
        return advertisementService.update(dtoMapper.map(object, Advertisement.class));
    }

    @PostMapping(value = "/create")
    public boolean createAdvertisement(@RequestBody @Validated(AdvertisementDto.class) AdvertisementDto object) {
        return advertisementService.create(dtoMapper.map(object, Advertisement.class));
    }
}
