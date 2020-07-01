package com.senlainc.javacourses.petushokvaliantsin.repository.user;

import com.senlainc.javacourses.petushokvaliantsin.model.user.UserCred;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserCred_;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserCredDao;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class UserCredDao extends AbstractDao<UserCred, Long> implements IUserCredDao {

    @Override
    public UserCred readByUsername(String username) {
        try {
            final CriteriaQuery<UserCred> criteriaQuery = criteriaBuilder.createQuery(UserCred.class);
            final Root<UserCred> root = criteriaQuery.from(UserCred.class);
            final Predicate predicate = criteriaBuilder.equal(root.get(UserCred_.username), username);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .getSingleResult();
        } catch (PersistenceException e) {
            throw new ReadQueryException(e);
        }
    }
}
