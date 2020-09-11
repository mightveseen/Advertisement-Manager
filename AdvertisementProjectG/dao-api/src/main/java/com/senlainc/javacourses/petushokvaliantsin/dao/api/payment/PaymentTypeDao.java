package com.senlainc.javacourses.petushokvaliantsin.dao.api.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;
import org.springframework.data.repository.CrudRepository;

public interface PaymentTypeDao extends CrudRepository<PaymentType, Long> {
}
