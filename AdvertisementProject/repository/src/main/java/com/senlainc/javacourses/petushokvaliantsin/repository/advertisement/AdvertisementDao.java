package com.senlainc.javacourses.petushokvaliantsin.repository.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.advertisement.IAdvertisementDao;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.sort.IPageParameter;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AdvertisementDao extends AbstractDao<Advertisement, Long> implements IAdvertisementDao {

    @Override
    public List<Advertisement> readAll(IPageParameter pageParameter) {
        try {
            final CriteriaQuery<Advertisement> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<Advertisement> root = criteriaQuery.from(entityClazz);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(pageParameter.getOrder(criteriaBuilder, root)))
                    .setFirstResult(pageParameter.getFirstElement())
                    .setMaxResults(pageParameter.getMaxResult())
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }
}
