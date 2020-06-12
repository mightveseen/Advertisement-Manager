package com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment;

import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;

public interface IPaymentService {

    boolean create(Long advertisementIndex, PaymentTypeDto paymentTypeDto);

    boolean delete(Long index);

    boolean update(Payment object);
}
