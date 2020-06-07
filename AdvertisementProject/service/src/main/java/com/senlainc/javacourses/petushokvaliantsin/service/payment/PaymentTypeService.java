package com.senlainc.javacourses.petushokvaliantsin.service.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.payment.IPaymentTypeDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment.IPaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentTypeService extends AbstractService<PaymentType, Long> implements IPaymentTypeService {

    private final IPaymentTypeDao paymentTypeDao;

    @Autowired
    public PaymentTypeService(IPaymentTypeDao paymentTypeDao) {
        this.paymentTypeDao = paymentTypeDao;
    }

    @Override
    public boolean create(PaymentType object) {
        paymentTypeDao.create(object);
        return true;
    }

    @Override
    public boolean delete(Long index) {
        paymentTypeDao.delete(paymentTypeDao.read(index));
        return true;
    }

    @Override
    public boolean update(PaymentType object) {
        paymentTypeDao.update(object);
        return true;
    }
}
