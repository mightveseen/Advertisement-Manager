package com.senlainc.javacourses.petushokvaliantsin.service.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment.IPaymentService;
import org.springframework.stereotype.Service;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

@Service
public class PaymentService implements IPaymentService {
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
    public <C> List<Payment> readAll(int firstElement, int maxResult, SingularAttribute<Payment, C> sortField) {
        return null;
    }
}
