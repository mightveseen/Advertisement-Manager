package com.senlainc.javacourses.petushokvaliantsin.repository.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory_;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement_;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IFilterParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IStateParameter;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdvertisementDao extends AbstractDao<Advertisement, Long> implements IAdvertisementDao {

    private static final String NONE_PARAMETER = "none";

    @Override
    public List<Advertisement> readAllWithUser(IPageParameter pageParameter, User user, State state) {
        try {
            final CriteriaQuery<Advertisement> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<Advertisement> root = criteriaQuery.from(entityClazz);
            final Predicate userPredicate = criteriaBuilder.equal(root.get(Advertisement_.user), user);
            final Predicate statePredicate = criteriaBuilder.equal(root.get(Advertisement_.state), state);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(getOrder(pageParameter, criteriaBuilder, root))
                    .where(criteriaBuilder.and(userPredicate, statePredicate)))
                    .setFirstResult(pageParameter.getFirstElement())
                    .setMaxResults(pageParameter.getMaxResult())
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public List<Advertisement> readAllWithFilter(IPageParameter pageParameter, IFilterParameter filterParameter, IStateParameter stateParameter) {
        try {
            final CriteriaQuery<Advertisement> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<Advertisement> root = criteriaQuery.from(entityClazz);
            final List<Predicate> predicates = getPredicates(root, filterParameter, stateParameter);
            final List<Order> orders = getOrders(pageParameter, root, stateParameter);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(orders).groupBy(root.get(Advertisement_.id))
                    .where(criteriaBuilder.and(predicates.toArray(new Predicate[0]))))
                    .setFirstResult(pageParameter.getFirstElement())
                    .setMaxResults(pageParameter.getMaxResult())
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public Long readCountWithState(State state) {
        try {
            final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            final Root<Advertisement> root = criteriaQuery.from(entityClazz);
            final Predicate predicate = criteriaBuilder.equal(root.get(Advertisement_.state), state);
            return entityManager.createQuery(criteriaQuery
                    .select(criteriaBuilder.count(root))
                    .where(predicate))
                    .getSingleResult();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    private List<Predicate> getPredicates(Root<Advertisement> root, IFilterParameter filterParameter, IStateParameter stateParameter) {
        final List<Predicate> predicates = new ArrayList<>(6);
        if (!filterParameter.getSearch().equals(NONE_PARAMETER)) {
            predicates.add(criteriaBuilder.like(root.get(Advertisement_.header), "%" + filterParameter.getSearch() + "%"));
        }
        if (!filterParameter.getCategory().equals(NONE_PARAMETER)) {
            final Join<Advertisement, AdvertisementCategory> join = root.join(Advertisement_.category);
            predicates.add(criteriaBuilder.equal(join.get(AdvertisementCategory_.description), filterParameter.getCategory()));
        }
        if (filterParameter.getMinPrice() > 0) {
            predicates.add(criteriaBuilder.ge(root.get(Advertisement_.price), filterParameter.getMinPrice()));
        }
        if (filterParameter.getMaxPrice() > 0) {
            predicates.add(criteriaBuilder.le(root.get(Advertisement_.price), filterParameter.getMaxPrice()));
        }
        if (!stateParameter.getAdvertisementState().equals(EnumState.ALL)) {
            predicates.add(criteriaBuilder.equal(root.get(Advertisement_.state), stateParameter.getAdvertisementState()));
        }
        return predicates;
    }

    //TODO : Sort by price/date and parse for paid and not
    //TODO : Try SQL query
    private List<Order> getOrders(IPageParameter pageParameter, Root<Advertisement> root, IStateParameter stateParameter) {
        final List<Order> orders = new ArrayList<>();
        final Join<Advertisement, Payment> join = root.join(Advertisement_.payments, JoinType.LEFT);
        join.on(criteriaBuilder.equal(join.get(Payment_.state), stateParameter.getPaymentState()));
        orders.add(criteriaBuilder.desc(join));
        orders.add(getOrder(pageParameter, criteriaBuilder, root));
        return orders;
    }
}
