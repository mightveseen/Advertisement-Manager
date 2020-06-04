package com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IGenericService;

public interface IPaymentService extends IGenericService<Payment, Long> {

    boolean create(Payment object);

    boolean delete(Long index);

    boolean update(Payment object);
}
