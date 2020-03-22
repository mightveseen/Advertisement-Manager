package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RoomDao extends AbstractDao<Room, Long> implements IRoomDao {

    @Override
    public List<Room> readAllFree() {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get("status"), RoomStatus.FREE);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .getResultList();
        } catch (PersistenceException e) {
            throw new ReadQueryException(ERROR + clazz.getSimpleName(), e);
        }
    }

    @Override
    public Long readFreeSize() {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get("status"), RoomStatus.FREE);
            return entityManager.createQuery(criteriaQuery
                    .select(criteriaBuilder.count(root))
                    .where(predicate))
                    .getSingleResult();
        } catch (PersistenceException e) {
            throw new ReadQueryException(ERROR + clazz.getSimpleName(), e);
        }
    }

    @Override
    public RoomStatus readStatus(long index) {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<RoomStatus> criteriaQuery = criteriaBuilder.createQuery(RoomStatus.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get("id"), index);
            return entityManager.createQuery(criteriaQuery
                    .select(root.get("status"))
                    .where(predicate))
                    .getSingleResult();
        } catch (PersistenceException e) {
            throw new ReadQueryException(ERROR + clazz.getSimpleName(), e);
        }
    }

    @Override
    public Boolean readByNumber(Integer number) {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get("number"), number);
            entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .getSingleResult();
            return Boolean.TRUE;
        } catch (PersistenceException e) {
            return Boolean.FALSE;
        }
    }
}
