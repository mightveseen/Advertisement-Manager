package com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IGenericService;

public interface IPaymentTypeService extends IGenericService<PaymentType, Long> {

    boolean create(PaymentType object);

    boolean delete(Long index);

    boolean update(PaymentType object);
}
