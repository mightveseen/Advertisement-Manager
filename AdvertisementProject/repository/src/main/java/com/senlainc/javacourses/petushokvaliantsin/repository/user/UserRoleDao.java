package com.senlainc.javacourses.petushokvaliantsin.repository.user;

import com.senlainc.javacourses.petushokvaliantsin.model.user.UserRole;
import com.senlainc.javacourses.petushokvaliantsin.model.user.UserRole_;
import com.senlainc.javacourses.petushokvaliantsin.repository.AbstractDao;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.user.IUserRoleDao;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class UserRoleDao extends AbstractDao<UserRole, Long> implements IUserRoleDao {

    @Override
    public UserRole readByDescription(String description) {
        try {
            final CriteriaQuery<UserRole> criteriaQuery = criteriaBuilder.createQuery(UserRole.class);
            final Root<UserRole> root = criteriaQuery.from(UserRole.class);
            final Predicate predicate = criteriaBuilder.equal(root.get(UserRole_.description), description);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .getSingleResult();
        } catch (PersistenceException e) {
            throw new ReadQueryException(e);
        }
    }
}
