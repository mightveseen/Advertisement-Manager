package com.senlainc.javacourses.petushokvaliantsin.repository.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.payment.IPaymentDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class PaymentDao extends AbstractDao<Payment, Long> implements IPaymentDao {

    @Override
    public Long readCountWithUser(User user) {
        final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        final Root<Payment> root = criteriaQuery.from(Payment.class);
        final Predicate predicate = criteriaBuilder.equal(root.get(Payment_.user), user);
        return entityManager.createQuery(criteriaQuery
                .select(criteriaBuilder.count(root))
                .where(predicate))
                .getSingleResult();
    }
}
