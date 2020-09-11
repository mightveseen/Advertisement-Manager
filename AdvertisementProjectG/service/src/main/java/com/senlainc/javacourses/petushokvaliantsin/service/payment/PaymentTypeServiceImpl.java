package com.senlainc.javacourses.petushokvaliantsin.service.payment;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.payment.PaymentTypeDao;
import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.payment.PaymentTypeService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PaymentTypeServiceImpl extends AbstractService implements PaymentTypeService {

    private static final Logger LOGGER = LogManager.getLogger(PaymentTypeServiceImpl.class);
    private final PaymentTypeDao paymentTypeDao;

    @Autowired
    public PaymentTypeServiceImpl(PaymentTypeDao paymentTypeDao) {
        this.paymentTypeDao = paymentTypeDao;
    }

    @Override
    @Transactional
    public boolean create(PaymentTypeDto object) {
        final PaymentType paymentType = dtoMapper.map(object, PaymentType.class);
        paymentTypeDao.save(paymentType);
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index) {
        if (!paymentTypeDao.existsById(index)) {
            throw new EntityNotExistException(entityNotExistMessage(7, 0, index));
        }
        paymentTypeDao.deleteById(index);
        LOGGER.info(EnumLogger.SUCCESSFUL_DELETE.getText());
        return true;
    }

    @Override
    @Transactional
    public boolean update(PaymentTypeDto object) {
        if (!paymentTypeDao.existsById(object.getId())) {
            throw new EntityNotExistException(entityNotExistMessage(7, 0, object.getId()));
        }
        final PaymentType paymentType = dtoMapper.map(object, PaymentType.class);
        paymentTypeDao.save(paymentType);
        LOGGER.info(EnumLogger.SUCCESSFUL_UPDATE.getText());
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentTypeDto> readAll() {
        final List<PaymentTypeDto> result = dtoMapper.mapAll(StreamSupport.stream(paymentTypeDao.findAll().spliterator(), false)
                .collect(Collectors.toList()), PaymentTypeDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }
}
