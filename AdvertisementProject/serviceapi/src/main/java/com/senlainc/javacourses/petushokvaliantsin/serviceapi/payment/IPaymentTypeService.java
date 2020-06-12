package com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment;

import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;

import java.util.List;

public interface IPaymentTypeService {

    boolean create(PaymentType object);

    boolean delete(Long index);

    boolean update(PaymentType object);

    List<PaymentTypeDto> getPaymentTypes();
}
