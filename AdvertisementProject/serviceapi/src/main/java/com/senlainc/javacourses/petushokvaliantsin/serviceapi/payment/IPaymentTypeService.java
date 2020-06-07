package com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;

public interface IPaymentTypeService {

    boolean create(PaymentType object);

    boolean delete(Long index);

    boolean update(PaymentType object);
}
