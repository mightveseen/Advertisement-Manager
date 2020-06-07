package com.senlainc.javacourses.petushokvaliantsin.service.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.payment.IPaymentDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentService extends AbstractService<Payment, Long> implements IPaymentService {

    private final IPaymentDao paymentDao;

    @Autowired
    public PaymentService(IPaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public boolean create(Payment object) {
        paymentDao.create(object);
        return true;
    }

    @Override
    public boolean delete(Long index) {
        paymentDao.delete(paymentDao.read(index));
        return true;
    }

    @Override
    public boolean update(Payment object) {
        paymentDao.update(object);
        return true;
    }
}
