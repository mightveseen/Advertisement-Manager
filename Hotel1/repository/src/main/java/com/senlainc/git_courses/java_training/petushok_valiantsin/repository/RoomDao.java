package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RoomDao extends AbstractDao<Room, Long> implements IRoomDao {

    private static final String ERROR = "Error during connection to Database. Check query.";

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
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
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
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public Room readByNumber(Integer number) {
        try {
            /* FIXME: Here drop error */
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Predicate predicate = criteriaBuilder.equal(root.get("number"), number);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .getSingleResult();
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
    }
}
