package com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment;

import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;

import java.util.List;

public interface IPaymentService {

    boolean create(Long advertisementIndex, PaymentTypeDto paymentTypeDto);

    List<PaymentDto> getUserPayments(UserDto userDto, int page, int max);

    Long getSize(Long user);
}
