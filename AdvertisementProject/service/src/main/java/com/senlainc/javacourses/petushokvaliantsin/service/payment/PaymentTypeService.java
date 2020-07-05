package com.senlainc.javacourses.petushokvaliantsin.service.payment;

import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.payment.IPaymentTypeDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment.IPaymentTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentTypeService extends AbstractService implements IPaymentTypeService {

    private static final Logger LOGGER = LogManager.getLogger(PaymentTypeService.class);
    private final IPaymentTypeDao paymentTypeDao;

    @Autowired
    public PaymentTypeService(IPaymentTypeDao paymentTypeDao) {
        this.paymentTypeDao = paymentTypeDao;
    }

    @Override
    @Transactional
    public boolean create(PaymentTypeDto object) {
        final PaymentType paymentType = dtoMapper.map(object, PaymentType.class);
        paymentTypeDao.create(paymentType);
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index) {
        paymentTypeDao.delete(paymentTypeDao.read(index));
        LOGGER.info(EnumLogger.SUCCESSFUL_DELETE.getText());
        return true;
    }

    @Override
    @Transactional
    public boolean update(PaymentTypeDto object) {
        final PaymentType paymentType = dtoMapper.map(object, PaymentType.class);
        paymentTypeDao.read(paymentType.getId());
        paymentTypeDao.update(paymentType);
        LOGGER.info(EnumLogger.SUCCESSFUL_UPDATE.getText());
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentTypeDto> readAll() {
        final List<PaymentTypeDto> result = dtoMapper.mapAll(paymentTypeDao.readAll(), PaymentTypeDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }
}
