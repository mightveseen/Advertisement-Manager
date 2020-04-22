package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RoomDao extends AbstractDao<Room, Long> implements IRoomDao {

    @Override
    public List<Room> readAllFree(int fistElement, int maxResult) {
        try {
            if (fistElement < 0) {
                fistElement = 0;
            }
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get("status"), RoomStatus.FREE);
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
            if (fistElement < 0) {
                fistElement = 0;
            }
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get("status"), RoomStatus.FREE);
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
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get("status"), RoomStatus.FREE);
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
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<RoomStatus> criteriaQuery = criteriaBuilder.createQuery(RoomStatus.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get("id"), index);
            return entityManager.createQuery(criteriaQuery
                    .select(root.get("status"))
                    .where(predicate))
                    .getSingleResult();
        } catch (PersistenceException e) {
            throw new ReadQueryException(e);
        }
    }

    @Override
    public boolean readByNumber(int number) {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get("number"), number);
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
