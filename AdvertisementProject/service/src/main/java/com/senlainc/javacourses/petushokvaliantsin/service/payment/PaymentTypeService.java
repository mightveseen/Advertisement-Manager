package com.senlainc.javacourses.petushokvaliantsin.service.payment;

import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.payment.IPaymentTypeDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment.IPaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentTypeService extends AbstractService<PaymentType, Long> implements IPaymentTypeService {

    private final IPaymentTypeDao paymentTypeDao;

    @Autowired
    public PaymentTypeService(IPaymentTypeDao paymentTypeDao) {
        this.paymentTypeDao = paymentTypeDao;
    }

    @Override
    @Transactional
    public boolean create(PaymentType object) {
        paymentTypeDao.create(object);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index) {
        paymentTypeDao.delete(paymentTypeDao.read(index));
        return true;
    }

    @Override
    @Transactional
    public boolean update(PaymentType object) {
        paymentTypeDao.read(object.getId());
        paymentTypeDao.update(object);
        return true;
    }

    @Override
    @Transactional
    public PaymentType read(Long index) {
        return paymentTypeDao.read(index);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentTypeDto> getPaymentTypes() {
        return dtoMapper.mapAll(paymentTypeDao.readAll(), PaymentTypeDto.class);
    }
}
