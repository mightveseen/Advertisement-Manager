package com.senlainc.javacourses.petushokvaliantsin.repository.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementCategory_;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IFilterParameter;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdvertisementDao extends AbstractDao<Advertisement, Long> implements IAdvertisementDao {

    @Override
    public List<Advertisement> readAllWithState(IPageParameter pageParameter, User user, State state) {
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
    public List<Advertisement> readAllWithFilter(IPageParameter pageParameter, IFilterParameter filterParameter, State state) {
        try {
            final CriteriaQuery<Advertisement> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<Advertisement> root = criteriaQuery.from(entityClazz);
            final List<Predicate> predicates = getPredicates(root, filterParameter, state);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(getOrder(pageParameter, criteriaBuilder, root))
                    .where(criteriaBuilder.and(predicates.toArray(new Predicate[0]))))
                    .setFirstResult(pageParameter.getFirstElement())
                    .setMaxResults(pageParameter.getMaxResult())
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    private List<Predicate> getPredicates(Root<Advertisement> root, IFilterParameter filterParameter, State state) {
        final List<Predicate> predicates = new ArrayList<>(5);
        if (!filterParameter.getSearch().equals("none")) {
            predicates.add(criteriaBuilder.like(root.get(Advertisement_.header), "%" + filterParameter.getSearch() + "%"));
        }
        if (!filterParameter.getCategory().equals("none")) {
            final Join<Advertisement, AdvertisementCategory> join = root.join(Advertisement_.category);
            predicates.add(criteriaBuilder.equal(join.get(AdvertisementCategory_.description), filterParameter.getCategory()));
        }
        if (filterParameter.getMinPrice() > 0) {
            predicates.add(criteriaBuilder.ge(root.get(Advertisement_.price), filterParameter.getMinPrice()));
        }
        if (filterParameter.getMaxPrice() > 0) {
            predicates.add(criteriaBuilder.le(root.get(Advertisement_.price), filterParameter.getMaxPrice()));
        }
        predicates.add(criteriaBuilder.equal(root.get(Advertisement_.state), state));
        return predicates;
    }
}
