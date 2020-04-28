package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room_;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

@Repository
public class RoomDao extends AbstractDao<Room, Long> implements IRoomDao {

    private static final SingularAttribute<Room, RoomStatus> STATUS_SINGULAR_ATTRIBUTE = Room_.status;

    @Override
    public List<Room> readAllFree(int fistElement, int maxResult) {
        try {
            final CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get(STATUS_SINGULAR_ATTRIBUTE), RoomStatus.FREE);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .setFirstResult(fistElement)
                    .getResultList();
        } catch (PersistenceException e) {
            throw new ReadQueryException(e);
        }
    }

    @Override
    public List<Room> readAllFree(int fistElement, int maxResult, String parameter) {
        try {
            final CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get(STATUS_SINGULAR_ATTRIBUTE), RoomStatus.FREE);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate)
                    .orderBy(criteriaBuilder.asc(root.get(parameter))))
                    .setFirstResult(fistElement)
                    .getResultList();
        } catch (PersistenceException e) {
            throw new ReadQueryException(e);
        }
    }

    @Override
    public Long readFreeSize() {
        try {
            final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get(STATUS_SINGULAR_ATTRIBUTE), RoomStatus.FREE);
            return entityManager.createQuery(criteriaQuery
                    .select(criteriaBuilder.count(root))
                    .where(predicate))
                    .getSingleResult();
        } catch (PersistenceException e) {
            throw new ReadQueryException(e);
        }
    }

    @Override
    public RoomStatus readStatus(long index) {
        try {
            final CriteriaQuery<RoomStatus> criteriaQuery = criteriaBuilder.createQuery(RoomStatus.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get(Room_.id), index);
            return entityManager.createQuery(criteriaQuery
                    .select(root.get(STATUS_SINGULAR_ATTRIBUTE))
                    .where(predicate))
                    .getSingleResult();
        } catch (PersistenceException e) {
            throw new ReadQueryException(e);
        }
    }

    @Override
    public boolean readByNumber(int number) {
        try {
            final CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get(Room_.number), number);
            entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .getSingleResult();
            return true;
        } catch (PersistenceException e) {
            return false;
        }
    }
}
