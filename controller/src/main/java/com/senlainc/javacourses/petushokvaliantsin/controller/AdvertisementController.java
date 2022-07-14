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
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
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
    @ResponseStatus(OK)
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

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public AdvertisementDto getAdvertisement(@PathVariable(name = "id") @Positive Long index) {
        return advertisementService.readByUser(index);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public Boolean deleteAdvertisement(@PathVariable(name = "id") @Positive Long index, @NotNull Principal principal) {
        return advertisementService.delete(principal.getName(), index);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public Boolean updateAdvertisement(@RequestBody @Validated({AdvertisementDto.Update.class, AdvertisementCategoryDto.Read.class})
                                       AdvertisementDto object, @NotNull Principal principal) {
        return advertisementService.updateByUser(principal.getName(), object);
    }

    @ResponseStatus(OK)
    @PostMapping("/create")
    public Boolean createAdvertisement(@RequestBody @Validated({AdvertisementDto.Create.class, AdvertisementCategoryDto.Read.class})
                                       AdvertisementDto object, @NotNull Principal principal) {
        return advertisementService.create(principal.getName(), object);
    }

    /**
     * Chat operation [Create chat]
     */
    @ResponseStatus(OK)
    @PostMapping("/{id}")
    public Boolean createChat(@PathVariable(name = "id") @Positive Long index, @NotNull Principal principal) {
        return chatService.create(principal.getName(), index);
    }

    /**
     * Comment operation [Show all advertisement comment's, add comment]
     */
    @ResponseStatus(OK)
    @GetMapping("/{id}/comments")
    public ResultListDto<AdvertisementCommentDto> getAdvertisementComments(@PathVariable(name = "id") @Positive Long index,
                                                                           @RequestParam(name = "first", defaultValue = "0") int firstElement,
                                                                           @RequestParam(name = "max", defaultValue = "15") int maxResult,
                                                                           @RequestParam(name = "direction", defaultValue = "desc") String direction,
                                                                           @RequestParam(name = "sort", defaultValue = "id") String sort) {
        return ResultListDto.of(advertisementCommentService.readSize(index),
                advertisementCommentService.readAll(index, firstElement, maxResult, direction, sort));
    }

    @ResponseStatus(OK)
    @PostMapping("/{id}/comments")
    public Boolean createAdvertisementComment(@PathVariable(name = "id") @Positive Long index,
                                              @RequestBody @Validated(AdvertisementCommentDto.Create.class) AdvertisementCommentDto object,
                                              @NotNull Principal principal) {
        return advertisementCommentService.create(principal.getName(), index, object);
    }

    /**
     * Payment operation [Show all payment type, add payment]
     */
    @ResponseStatus(OK)
    @GetMapping("{id}/payments")
    public List<PaymentTypeDto> getPaymentTypes() {
        return paymentTypeService.readAll();
    }

    @ResponseStatus(OK)
    @PostMapping("{id}/payments")
    public Boolean addPayment(@PathVariable(name = "id") Long index,
                              @RequestBody @Validated(PaymentTypeDto.Read.class) PaymentTypeDto object,
                              @NotNull Principal principal) {
        return paymentService.create(principal.getName(), index, object);
    }
}
