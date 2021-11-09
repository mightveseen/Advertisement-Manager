package com.senlainc.javacourses.petushokvaliantsin.service.payment;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.StateDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.advertisement.AdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.payment.PaymentDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.payment.PaymentTypeDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.user.UserDao;
import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.payment.PaymentService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.ExceededLimitException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.PermissionDeniedException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@PropertySource(value = "classpath:/properties/service.properties", ignoreResourceNotFound = true)
public class PaymentServiceImpl extends AbstractService implements PaymentService {

    private static final Logger LOGGER = LogManager.getLogger(PaymentServiceImpl.class);
    private final PaymentDao paymentDao;
    private final AdvertisementDao advertisementDao;
    private final PaymentTypeDao paymentTypeDao;
    private final StateDao stateDao;
    private final UserDao userDao;
    @Value("${PAYMENT.ACTIVE_LIMIT:3}")
    private Short activeLimit;

    @Override
    @Transactional
    public boolean create(String username, Long advertisementIndex, PaymentTypeDto object) {
        final Advertisement advertisement = advertisementDao.readById(advertisementIndex).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(0, 0, advertisementIndex)));
        checkPermission(advertisement, username);
        final State state = stateDao.readByDescription(EnumState.APPROVED.name()).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(1, 1, EnumState.APPROVED.name())));
        final List<Payment> advertisementActivePayment = advertisement.getPayments().stream().filter(i -> i.getState().equals(state))
                .collect(Collectors.toList());
        checkLimit(advertisementActivePayment.size());
        final LocalDate paymentStartDate = createLocalDate(advertisementActivePayment);
        final PaymentType paymentType = paymentTypeDao.findById(object.getId()).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(6, 0, object.getId())));
        paymentDao.save(new Payment(advertisement.getUser(), advertisement, paymentType, paymentStartDate,
                paymentStartDate.plusDays(paymentType.getDuration()), paymentType.getPrice(), state));
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional
    public List<PaymentDto> readAll(String username, int page, int max) {
        final List<PaymentDto> result = dtoMapper.mapAll(paymentDao.readAllByUser(PageRequest.of(page, max),
                userDao.readByUserCred(username).orElseThrow(() ->
                        new EntityNotExistException(entityNotExistMessage(2, 2, username)))), PaymentDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Long readSize(String username) {
        return paymentDao.countAllByUser(userDao.readByUserCred(username).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(2, 2, username))));
    }

    private LocalDate createLocalDate(List<Payment> advertisementActivePayment) {
        return (advertisementActivePayment == null || advertisementActivePayment.isEmpty()) ? LocalDate.now() :
                advertisementActivePayment.stream().max(Comparator.comparing(Payment::getEndDate)).orElseThrow().getEndDate();
    }

    private void checkPermission(Advertisement advertisement, String username) {
        if (!advertisement.getUser().getId().equals(userDao.readByUserCred(username).orElseThrow(() ->
                new EntityNotExistException(entityNotExistMessage(2, 2, username))).getId())) {
            throw new PermissionDeniedException(EnumException.PERMISSION.getMessage());
        }
    }

    private void checkLimit(int paymentSize) {
        if (paymentSize >= activeLimit) {
            throw new ExceededLimitException(String.format(EnumException.ACTIVE_PAYMENT.getMessage(), activeLimit));
        }
    }
}
