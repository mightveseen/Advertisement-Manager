package com.senlainc.javacourses.petushokvaliantsin.controller;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCommentDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementCommentService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.chat.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final IChatService chatService;
    private final IAdvertisementService advertisementService;
    private final IAdvertisementCommentService advertisementCommentService;


    @Autowired
    public AdvertisementController(IAdvertisementService advertisementService, IChatService chatService,
                                   IAdvertisementCommentService advertisementCommentService) {
        this.advertisementService = advertisementService;
        this.chatService = chatService;
        this.advertisementCommentService = advertisementCommentService;
    }

    @GetMapping
    public List<AdvertisementDto> getAdvertisements(@RequestParam(name = "page", defaultValue = "1") @Positive int page,
                                                    @RequestParam(name = "number", defaultValue = "15") @Positive int numberElements,
                                                    @RequestParam(name = "direction", defaultValue = "desc") String direction,
                                                    @RequestParam(name = "sort", defaultValue = "user-rating") String sort,
                                                    @RequestParam(name = "cat", defaultValue = "none") String category,
                                                    @RequestParam(name = "search", defaultValue = "none") String search,
                                                    @RequestParam(name = "min", defaultValue = "0") double minPrice,
                                                    @RequestParam(name = "max", defaultValue = "0") double maxPrice) {
        return advertisementService.getAdvertisements(page, numberElements, direction, sort, search, category, minPrice, maxPrice);
    }

    @GetMapping(value = "/{id}")
    public AdvertisementDto getAdvertisement(@PathVariable(name = "id") @Positive Long index) {
        return advertisementService.getAdvertisement(index);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> createChat(@PathVariable(name = "id") @Positive Long index,
                                             @RequestBody @Validated(ChatDto.class) ChatDto chat) {
        return new ResponseEntity<>(chatService.create(chat), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/comments")
    public List<AdvertisementCommentDto> getAdvertisementComments(@PathVariable(name = "id") @Positive Long index,
                                                                  @RequestParam(name = "first", defaultValue = "0") int firstElement,
                                                                  @RequestParam(name = "max", defaultValue = "15") int maxResult,
                                                                  @RequestParam(name = "direction", defaultValue = "desc") String direction,
                                                                  @RequestParam(name = "sort", defaultValue = "default") String sort) {
        return advertisementCommentService.getAdvertisementComments(index, firstElement, maxResult, direction, sort);
    }

    @PostMapping(value = "/{id}/comments")
    public ResponseEntity<Object> createAdvertisementComment(@PathVariable(name = "id") @Positive Long index,
                                                             @RequestBody @Validated(AdvertisementCommentDto.class) AdvertisementCommentDto object) {
        return new ResponseEntity<>(advertisementCommentService.create(index, object), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAdvertisement(@PathVariable(name = "id") @Positive Long index) {
        return new ResponseEntity<>(advertisementService.delete(index), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateAdvertisement(@RequestBody @Validated(AdvertisementDto.class) AdvertisementDto object) {
        return new ResponseEntity<>(advertisementService.update(object), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createAdvertisement(@RequestBody @Validated(AdvertisementDto.class) AdvertisementDto object) {
        return new ResponseEntity<>(advertisementService.create(object), HttpStatus.OK);
    }
}
