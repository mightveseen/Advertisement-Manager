package com.senlainc.javacourses.petushokvaliantsin.repository.user;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserRating;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserRating_;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserRatingDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class UserRatingDao extends AbstractDao<UserRating, Long> implements IUserRatingDao {

    @Override
    public Long readCount(User rateOwnerUser, User ratedUser) {
        final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        final Root<UserRating> root = criteriaQuery.from(entityClazz);
        final Predicate rateOwnerUserPredicate = criteriaBuilder.equal(root.get(UserRating_.rateOwnerUser), rateOwnerUser);
        final Predicate ratedUserPredicate = criteriaBuilder.equal(root.get(UserRating_.ratedUser), ratedUser);
        return entityManager.createQuery(criteriaQuery
                .select(criteriaBuilder.count(root))
                .where(criteriaBuilder.and(ratedUserPredicate, rateOwnerUserPredicate)))
                .getSingleResult();
    }
}
