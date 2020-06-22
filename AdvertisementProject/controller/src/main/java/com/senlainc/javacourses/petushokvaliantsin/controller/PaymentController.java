package com.senlainc.javacourses.petushokvaliantsin.controller;

import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment.IPaymentService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment.IPaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/advertisements/{id}/payments")
public class PaymentController {

    private final IPaymentTypeService paymentTypeService;
    private final IPaymentService paymentService;

    @Autowired
    public PaymentController(IPaymentService paymentService, IPaymentTypeService paymentTypeService) {
        this.paymentService = paymentService;
        this.paymentTypeService = paymentTypeService;
    }

    @GetMapping
    public List<PaymentTypeDto> getPaymentTypes() {
        return paymentTypeService.getPaymentTypes();
    }

    @PostMapping
    public ResponseEntity<Object> addPayment(@PathVariable(name = "id") Long index,
                                             @RequestBody @Validated(PaymentTypeDto.class) PaymentTypeDto object) {
        return new ResponseEntity<>(paymentService.create(index, object), HttpStatus.OK);
    }
}
