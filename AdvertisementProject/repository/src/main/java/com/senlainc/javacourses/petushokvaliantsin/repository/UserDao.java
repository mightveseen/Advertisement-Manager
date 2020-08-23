package com.senlainc.javacourses.petushokvaliantsin.repository;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserCred_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserDao;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class UserDao extends AbstractDao<User, Long> implements IUserDao {

    //TODO : Fix Optional
    @Override
    public Optional<User> readByUserCred(String username) {
        try {
            final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            final Root<User> root = criteriaQuery.from(User.class);
            final Predicate predicate = criteriaBuilder.equal(root.get(User_.userCred).get(UserCred_.username), username);
            return Optional.ofNullable(entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .setHint(GraphProperty.Type.FETCH, entityManager.getEntityGraph(GraphProperty.User.DEFAULT))
                    .getSingleResult());
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public Optional<User> readByEmail(String email) {
        try {
            final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            final Root<User> root = criteriaQuery.from(User.class);
            final Predicate predicate = criteriaBuilder.equal(root.get(User_.email), email);
            return Optional.ofNullable(entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .setHint(GraphProperty.Type.FETCH, entityManager.getEntityGraph(GraphProperty.User.DEFAULT))
                    .getSingleResult());
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public Optional<User> readByPhone(Integer phone) {
        try {
            final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            final Root<User> root = criteriaQuery.from(User.class);
            final Predicate predicate = criteriaBuilder.equal(root.get(User_.phone), phone);
            return Optional.ofNullable(entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .setHint(GraphProperty.Type.FETCH, entityManager.getEntityGraph(GraphProperty.User.DEFAULT))
                    .getSingleResult());
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }
}
