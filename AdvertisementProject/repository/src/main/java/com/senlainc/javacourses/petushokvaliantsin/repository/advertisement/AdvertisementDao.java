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
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
                    .orderBy(pageParameter.getOrder(criteriaBuilder, root))
                    .where(criteriaBuilder.and(userPredicate, statePredicate)))
                    .setFirstResult(pageParameter.getFirstElement())
                    .setMaxResults(pageParameter.getMaxResult())
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public <F> List<Advertisement> readAllWithSearch(IPageParameter pageParameter, F value) {
        try {
            final CriteriaQuery<Advertisement> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<Advertisement> root = criteriaQuery.from(entityClazz);
            final Predicate predicate = criteriaBuilder.equal(root.get(Advertisement_.header), value);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(pageParameter.getOrder(criteriaBuilder, root))
                    .where(predicate))
                    .setFirstResult(pageParameter.getFirstElement())
                    .setMaxResults(pageParameter.getMaxResult())
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public <F> List<Advertisement> readAllWithCategory(IPageParameter pageParameter, F value) {
        try {
            final CriteriaQuery<Advertisement> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<Advertisement> root = criteriaQuery.from(entityClazz);
            final Join<Advertisement, AdvertisementCategory> join = root.join(Advertisement_.category);
            final Predicate predicate = criteriaBuilder.equal(join.get(AdvertisementCategory_.description), value);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(pageParameter.getOrder(criteriaBuilder, root))
                    .where(predicate))
                    .setFirstResult(pageParameter.getFirstElement())
                    .setMaxResults(pageParameter.getMaxResult())
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }
}
