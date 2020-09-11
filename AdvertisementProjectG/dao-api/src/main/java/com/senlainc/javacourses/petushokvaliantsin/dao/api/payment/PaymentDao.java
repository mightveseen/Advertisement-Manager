package com.senlainc.javacourses.petushokvaliantsin.dao.api.payment;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentDao extends CrudRepository<Payment, Long> {

    @EntityGraph(value = GraphProperty.PAYMENT_DEFAULT)
    List<Payment> readAllByUser(Pageable pageable, User user);

    Long countAllByUser(User user);
}
