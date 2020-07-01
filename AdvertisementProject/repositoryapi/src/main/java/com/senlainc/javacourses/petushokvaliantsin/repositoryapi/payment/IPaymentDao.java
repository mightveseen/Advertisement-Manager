package com.senlainc.javacourses.petushokvaliantsin.repositoryapi.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IGenericDao;

public interface IPaymentDao extends IGenericDao<Payment, Long> {

    Long readCountWithUser(User user);
}
