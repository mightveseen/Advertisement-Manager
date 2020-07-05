package com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment;

import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;

import java.util.List;

public interface IPaymentTypeService {

    boolean create(PaymentTypeDto object);

    boolean delete(Long index);

    boolean update(PaymentTypeDto object);

    List<PaymentTypeDto> readAll();
}
