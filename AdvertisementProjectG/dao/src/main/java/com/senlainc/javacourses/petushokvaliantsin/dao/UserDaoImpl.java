package com.senlainc.javacourses.petushokvaliantsin.dao;

import com.senlainc.javacourses.petushokvaliantsin.dao.api.customquery.UserDaoCustomQuery;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserCred_;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User_;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDaoCustomQuery {

    @Override
    public Optional<User> readByUserCred(String username) {
        try {
            final List<User> result = queryReadByUserCred(username).getResultList();
            return result == null ? Optional.empty() : Optional.of(result.get(0));
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public Optional<User> readByUserCred(String username, String graphName) {
        try {
            final List<User> result = queryReadByUserCred(username)
                    .setHint(GraphProperty.Type.FETCH, entityManager.getEntityGraph(graphName))
                    .getResultList();
            return result == null ? Optional.empty() : Optional.of(result.get(0));
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public Optional<User> readByEmail(String email) {
        try {
            final List<User> result = queryReadByEmail(email).getResultList();
            return result == null ? Optional.empty() : Optional.of(result.get(0));
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public Optional<User> readByEmail(String email, String graphName) {
        try {
            final List<User> result = queryReadByEmail(email)
                    .setHint(GraphProperty.Type.FETCH, entityManager.getEntityGraph(graphName))
                    .getResultList();
            return result == null ? Optional.empty() : Optional.of(result.get(0));
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public Optional<User> readByPhone(Integer phone) {
        try {
            final List<User> result = queryReadByPhone(phone).getResultList();
            return result == null ? Optional.empty() : Optional.of(result.get(0));
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public Optional<User> readByPhone(Integer phone, String graphName) {
        try {
            final List<User> result = queryReadByPhone(phone)
                    .setHint(GraphProperty.Type.FETCH, entityManager.getEntityGraph(GraphProperty.User.DEFAULT))
                    .getResultList();
            return result == null ? Optional.empty() : Optional.of(result.get(0));
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    private TypedQuery<User> queryReadByUserCred(String username) {
        final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        final Root<User> root = criteriaQuery.from(User.class);
        final Predicate predicate = criteriaBuilder.equal(root.get(User_.userCred).get(UserCred_.username), username);
        return entityManager.createQuery(criteriaQuery
                .select(root)
                .where(predicate));
    }

    private TypedQuery<User> queryReadByEmail(String email) {
        final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        final Root<User> root = criteriaQuery.from(User.class);
        final Predicate predicate = criteriaBuilder.equal(root.get(User_.email), email);
        return entityManager.createQuery(criteriaQuery
                .select(root)
                .where(predicate));
    }

    private TypedQuery<User> queryReadByPhone(Integer phone) {
        final CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        final Root<User> root = criteriaQuery.from(User.class);
        final Predicate predicate = criteriaBuilder.equal(root.get(User_.phone), phone);
        return entityManager.createQuery(criteriaQuery
                .select(root)
                .where(predicate));
    }
}
