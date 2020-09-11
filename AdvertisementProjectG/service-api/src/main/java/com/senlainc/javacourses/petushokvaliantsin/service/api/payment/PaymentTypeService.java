package com.senlainc.javacourses.petushokvaliantsin.service.api.payment;

import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;

import java.util.List;

public interface PaymentTypeService {

    boolean create(PaymentTypeDto object);

    boolean delete(Long index);

    boolean update(PaymentTypeDto object);

    List<PaymentTypeDto> readAll();
}
