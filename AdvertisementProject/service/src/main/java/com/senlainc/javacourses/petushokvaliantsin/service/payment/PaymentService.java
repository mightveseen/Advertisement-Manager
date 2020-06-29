package com.senlainc.javacourses.petushokvaliantsin.service.payment;

import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.payment.PaymentTypeDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.PaymentType;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IStateDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.payment.IPaymentDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.payment.IPaymentTypeDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.service.AbstractService;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.payment.IPaymentService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.ExceededLimitException;
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
public class PaymentService extends AbstractService<Payment, Long> implements IPaymentService {

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

    //TODO : Should take DTO data or take dto id and read data from DataBase?
    @Override
    @Transactional
    public boolean create(Long advertisementIndex, PaymentTypeDto paymentTypeDto) {
        final Advertisement advertisement = advertisementDao.read(advertisementIndex);
        final State state = stateDao.read(EnumState.APPROVED.name());
        final List<Payment> advertisementActivePayment = advertisement.getPayments().stream().filter(i -> i.getState().equals(state)).collect(Collectors.toList());
        if (advertisementActivePayment.size() >= activeLimit) {
            throw new ExceededLimitException("You have: [" + activeLimit + "] active payment's");
        }
        final LocalDate paymentStartDate = (!advertisementActivePayment.isEmpty()) ?
                advertisementActivePayment.stream().max(Comparator.comparing(Payment::getEndDate)).get().getEndDate() :
                LocalDate.now();
        final PaymentType paymentType = paymentTypeDao.read(paymentTypeDto.getId());
        paymentDao.create(new Payment(advertisement.getUser(), advertisement, paymentType, paymentStartDate,
                paymentStartDate.plusDays(paymentType.getDuration()), paymentType.getPrice(), state));
        return true;
    }

    @Override
    @Transactional
    public List<PaymentDto> getUserPayments(UserDto userDto, int page, int max) {
        final User user = userDao.read(userDto.getId());
        final IPageParameter pageParameter = PageParameter.of(page, max);
        return dtoMapper.mapAll(paymentDao.readAll(pageParameter, Payment_.user, user), PaymentDto.class);
    }
}
