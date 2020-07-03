package com.senlainc.javacourses.petushokvaliantsin.service.payment;

import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumException;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IStateDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.payment.IPaymentDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.payment.IPaymentTypeDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment.IPaymentService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.ExceededLimitException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.PermissionDeniedException;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource(value = "classpath:/properties/service.properties", ignoreResourceNotFound = true)
public class PaymentService extends AbstractService implements IPaymentService {

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
    public boolean create(String username, Long advertisementIndex, PaymentTypeDto paymentTypeDto) {
        final Advertisement advertisement = advertisementDao.read(advertisementIndex);
        checkPermission(advertisement, username);
        final State state = stateDao.readByDescription(EnumState.APPROVED.name());
        final List<Payment> advertisementActivePayment = advertisement.getPayments().stream().filter(i -> i.getState().equals(state)).collect(Collectors.toList());
        checkLimit(advertisementActivePayment.size());
        final LocalDate paymentStartDate = createLocalDate(advertisementActivePayment);
        final PaymentType paymentType = paymentTypeDao.read(paymentTypeDto.getId());
        paymentDao.create(new Payment(advertisement.getUser(), advertisement, paymentType, paymentStartDate,
                paymentStartDate.plusDays(paymentType.getDuration()), paymentType.getPrice(), state));
        return true;
    }

    @Override
    @Transactional
    public List<PaymentDto> getUserPayments(String username, int page, int max) {
        final IPageParameter pageParameter = PageParameter.of(page, max);
        return dtoMapper.mapAll(paymentDao.readAll(pageParameter, Payment_.user, userDao.readByUserCred(username)), PaymentDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getSize(String username) {
        return paymentDao.readCountWithUser(userDao.readByUserCred(username));
    }

    private LocalDate createLocalDate(List<Payment> advertisementActivePayment) {
        return (advertisementActivePayment.isEmpty()) ? LocalDate.now() :
                advertisementActivePayment.stream().max(Comparator.comparing(Payment::getEndDate)).get().getEndDate();
    }

    private void checkPermission(Advertisement advertisement, String username) {
        if (!advertisement.getUser().getId().equals(userDao.readByUserCred(username).getId())) {
            throw new PermissionDeniedException(EnumException.PERMISSION.getMessage());
        }
    }

    private void checkLimit(int paymentSize) {
        if (paymentSize >= activeLimit) {
            throw new ExceededLimitException(String.format(EnumException.ACTIVE_PAYMENT.getMessage(), activeLimit));
        }
    }
}
