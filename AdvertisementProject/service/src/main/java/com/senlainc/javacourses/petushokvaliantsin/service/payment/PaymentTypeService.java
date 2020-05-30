package com.senlainc.javacourses.petushokvaliantsin.service.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment.IPaymentTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeService extends AbstractService<PaymentType, Long> implements IPaymentTypeService {
    @Override
    public boolean create(PaymentType object) {
        return false;
    }

    @Override
    public boolean remove(Long index) {
        return false;
    }

    @Override
    public boolean update(PaymentType object) {
        return false;
    }

    @Override
    public PaymentType read(Long index) {
        return null;
    }

    @Override
    public List<PaymentType> readAll(int firstElement, int maxResult) {
        return null;
    }

    @Override
    public List<PaymentType> readAll(int firstElement, int maxResult, String sortField) {
        return null;
    }
}
