package com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment;

import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;

import java.util.List;

public interface IPaymentService {

    boolean create(String username, Long advertisementIndex, PaymentTypeDto object);

    List<PaymentDto> readAll(String username, int page, int max);

    Long readSize(String username);
}
