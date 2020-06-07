package com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;

public interface IPaymentService {

    boolean create(Payment object);

    boolean delete(Long index);

    boolean update(Payment object);
}
