package com.senlainc.javacourses.petushokvaliantsin.repository.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.payment.IPaymentDao;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDao extends AbstractDao<Payment, Long> implements IPaymentDao {
}
