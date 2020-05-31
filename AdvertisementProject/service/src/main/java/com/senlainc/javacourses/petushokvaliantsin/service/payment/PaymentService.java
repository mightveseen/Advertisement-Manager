package com.senlainc.javacourses.petushokvaliantsin.service.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment.IPaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService extends AbstractService<Payment, Long> implements IPaymentService {
    @Override
    public boolean create(Payment object) {
        return false;
    }

    @Override
    public boolean remove(Long index) {
        return false;
    }

    @Override
    public boolean update(Payment object) {
        return false;
    }

    @Override
    public Payment read(Long index) {
        return null;
    }

    @Override
    public List<Payment> readAll(int firstElement, int maxResult) {
        return null;
    }

    @Override
    public List<Payment> readAll(int firstElement, int maxResult, String direction, String sortField) {
        return null;
    }
}
