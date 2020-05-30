package com.senlainc.javacourses.petushokvaliantsin.repository.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.payment.IPaymentTypeDao;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentTypeDao extends AbstractDao<PaymentType, Long> implements IPaymentTypeDao {

}
