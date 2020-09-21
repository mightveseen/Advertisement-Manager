package com.senlainc.javacourses.petushokvaliantsin.controller;

import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCategoryDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCommentDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.combination.ResultListDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
import com.senlainc.javacourses.petushokvaliantsin.service.api.advertisement.AdvertisementCommentService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.advertisement.AdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.chat.ChatService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.payment.PaymentService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.payment.PaymentTypeService;
import lombok.RequiredArgsConstructor;
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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "advertisements")
public class AdvertisementController {

    private final ChatService chatService;
    private final AdvertisementService advertisementService;
    private final AdvertisementCommentService advertisementCommentService;
    private final PaymentService paymentService;
    private final PaymentTypeService paymentTypeService;

    /**
     * Advertisement operation [Show all, show by id, delete/remove/update]
     */
    @GetMapping
    public ResultListDto<AdvertisementDto> getAdvertisements(@RequestParam(name = "page", defaultValue = "0") @Positive int page,
                                                             @RequestParam(name = "number", defaultValue = "15") @Positive int numberElements,
                                                             @RequestParam(name = "direction", defaultValue = "desc") String direction,
                                                             @RequestParam(name = "sort", defaultValue = "user-rating") String sort,
                                                             @RequestParam(name = "cat", defaultValue = "none") String category,
                                                             @RequestParam(name = "search", defaultValue = "none") String search,
                                                             @RequestParam(name = "min", defaultValue = "0") double minPrice,
                                                             @RequestParam(name = "max", defaultValue = "0") double maxPrice) {
        return ResultListDto.of(advertisementService.readSize(search, category, minPrice, maxPrice, EnumState.ACTIVE),
                advertisementService.readAll(page, numberElements, direction, sort, search, category, minPrice, maxPrice, EnumState.ACTIVE));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisement(@PathVariable(name = "id") @Positive Long index) {
        return new ResponseEntity<>(advertisementService.readByUser(index), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteAdvertisement(@PathVariable(name = "id") @Positive Long index, @NotNull Principal principal) {
        return new ResponseEntity<>(advertisementService.delete(principal.getName(), index), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Boolean> updateAdvertisement(@RequestBody @Validated({AdvertisementDto.Update.class, AdvertisementCategoryDto.Read.class})
                                                               AdvertisementDto object, @NotNull Principal principal) {
        return new ResponseEntity<>(advertisementService.updateByUser(principal.getName(), object), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Boolean> createAdvertisement(@RequestBody @Validated({AdvertisementDto.Create.class, AdvertisementCategoryDto.Read.class})
                                                               AdvertisementDto object, @NotNull Principal principal) {
        return new ResponseEntity<>(advertisementService.create(principal.getName(), object), HttpStatus.OK);
    }

    /**
     * Chat operation [Create chat]
     */
    @PostMapping(value = "/{id}")
    public ResponseEntity<Boolean> createChat(@PathVariable(name = "id") @Positive Long index, @NotNull Principal principal) {
        return new ResponseEntity<>(chatService.create(principal.getName(), index), HttpStatus.OK);
    }

    /**
     * Comment operation [Show all advertisement comment's, add comment]
     */
    @GetMapping(value = "/{id}/comments")
    public ResultListDto<AdvertisementCommentDto> getAdvertisementComments(@PathVariable(name = "id") @Positive Long index,
                                                                           @RequestParam(name = "first", defaultValue = "0") int firstElement,
                                                                           @RequestParam(name = "max", defaultValue = "15") int maxResult,
                                                                           @RequestParam(name = "direction", defaultValue = "desc") String direction,
                                                                           @RequestParam(name = "sort", defaultValue = "id") String sort) {
        return ResultListDto.of(advertisementCommentService.readSize(index),
                advertisementCommentService.readAll(index, firstElement, maxResult, direction, sort));
    }

    @PostMapping(value = "/{id}/comments")
    public ResponseEntity<Boolean> createAdvertisementComment(@PathVariable(name = "id") @Positive Long index,
                                                              @RequestBody @Validated(AdvertisementCommentDto.Create.class) AdvertisementCommentDto object,
                                                              @NotNull Principal principal) {
        return new ResponseEntity<>(advertisementCommentService.create(principal.getName(), index, object), HttpStatus.OK);
    }

    /**
     * Payment operation [Show all payment type, add payment]
     */
    @GetMapping(value = "{id}/payments")
    public List<PaymentTypeDto> getPaymentTypes() {
        return paymentTypeService.readAll();
    }

    @PostMapping(value = "{id}/payments")
    public ResponseEntity<Boolean> addPayment(@PathVariable(name = "id") Long index,
                                              @RequestBody @Validated(PaymentTypeDto.Read.class) PaymentTypeDto object,
                                              @NotNull Principal principal) {
        return new ResponseEntity<>(paymentService.create(principal.getName(), index, object), HttpStatus.OK);
    }
}
