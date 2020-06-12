package com.senlainc.javacourses.petushokvaliantsin.service.payment;

import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.payment.IPaymentDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.IStateService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class PaymentService extends AbstractService<Payment, Long> implements IPaymentService {

    private final IPaymentDao paymentDao;
    private final IAdvertisementService advertisementService;
    private final IStateService stateService;

    @Autowired
    public PaymentService(IPaymentDao paymentDao, IAdvertisementService advertisementService, IStateService stateService) {
        this.paymentDao = paymentDao;
        this.advertisementService = advertisementService;
        this.stateService = stateService;
    }

    @Override
    @Transactional
    public boolean create(Long advertisementIndex, PaymentTypeDto paymentTypeDto) {
        final PaymentType paymentType = dtoMapper.map(paymentTypeDto, PaymentType.class);
        final Payment payment = new Payment(advertisementService.read(advertisementIndex), paymentType, LocalDate.now(),
                LocalDate.now().plusDays(paymentType.getDuration()), paymentType.getPrice(), stateService.read("APPROVED"));
        paymentDao.create(payment);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index) {
        paymentDao.delete(paymentDao.read(index));
        return true;
    }

    @Override
    @Transactional
    public boolean update(Payment object) {
        paymentDao.update(object);
        return true;
    }
}
