package com.senlainc.javacourses.petushokvaliantsin.service.payment;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.IStateDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.payment.IPaymentDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.payment.IPaymentTypeDao;
import com.senlainc.javacourses.petushokvaliantsin.dao.api.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumLogger;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.service.api.payment.IPaymentService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.ExceededLimitException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.PermissionDeniedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource(value = "classpath:/properties/service.properties", ignoreResourceNotFound = true)
public class PaymentService extends AbstractService implements IPaymentService {

    private static final Logger LOGGER = LogManager.getLogger(PaymentService.class);
    private final IPaymentDao paymentDao;
    private final IAdvertisementDao advertisementDao;
    private final IPaymentTypeDao paymentTypeDao;
    private final IStateDao stateDao;
    private final IUserDao userDao;
    @Value("${PAYMENT.ACTIVE_LIMIT:3}")
    private Short activeLimit;

    @Autowired
    public PaymentService(IPaymentDao paymentDao, IAdvertisementDao advertisementDao, IStateDao stateDao,
                          IPaymentTypeDao paymentTypeDao, IUserDao userDao) {
        this.paymentDao = paymentDao;
        this.advertisementDao = advertisementDao;
        this.paymentTypeDao = paymentTypeDao;
        this.stateDao = stateDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean create(String username, Long advertisementIndex, PaymentTypeDto object) {
        final Advertisement advertisement = advertisementDao.read(advertisementIndex, GraphProperty.Advertisement.DEFAULT).orElseThrow();
        checkPermission(advertisement, username);
        final State state = stateDao.readByDescription(EnumState.APPROVED.name()).orElseThrow();
        final List<Payment> advertisementActivePayment = advertisement.getPayments().stream().filter(i -> i.getState().equals(state)).collect(Collectors.toList());
        checkLimit(advertisementActivePayment.size());
        final LocalDate paymentStartDate = createLocalDate(advertisementActivePayment);
        final PaymentType paymentType = paymentTypeDao.findById(object.getId()).orElseThrow(EntityNotFoundException::new);
        paymentDao.save(new Payment(advertisement.getUser(), advertisement, paymentType, paymentStartDate,
                paymentStartDate.plusDays(paymentType.getDuration()), paymentType.getPrice(), state));
        LOGGER.info(EnumLogger.SUCCESSFUL_CREATE.getText());
        return true;
    }

    @Override
    @Transactional
    public List<PaymentDto> readAll(String username, int page, int max) {
        final List<PaymentDto> result = dtoMapper.mapAll(paymentDao.readAllByUser(PageRequest.of(page, max), userDao.readByUserCred(username).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_NOT_EXIST.getMessage(), CLASSES[2], CLASS_FIELDS[2], username)))), PaymentDto.class);
        LOGGER.info(EnumLogger.SUCCESSFUL_READ.getText());
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Long readSize(String username) {
        return paymentDao.countAllByUser(userDao.readByUserCred(username).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_NOT_EXIST.getMessage(), CLASSES[2], CLASS_FIELDS[2], username))));
    }

    private LocalDate createLocalDate(List<Payment> advertisementActivePayment) {
        return (advertisementActivePayment == null || advertisementActivePayment.isEmpty()) ? LocalDate.now() :
                advertisementActivePayment.stream().max(Comparator.comparing(Payment::getEndDate)).get().getEndDate();
    }

    private void checkPermission(Advertisement advertisement, String username) {
        if (!advertisement.getUser().getId().equals(userDao.readByUserCred(username).orElseThrow(() ->
                new EntityNotExistException(String.format(EnumException.ENTITY_NOT_EXIST.getMessage(), CLASSES[2], CLASS_FIELDS[2], username))).getId())) {
            throw new PermissionDeniedException(EnumException.PERMISSION.getMessage());
        }
    }

    private void checkLimit(int paymentSize) {
        if (paymentSize >= activeLimit) {
            throw new ExceededLimitException(String.format(EnumException.ACTIVE_PAYMENT.getMessage(), activeLimit));
        }
    }
}