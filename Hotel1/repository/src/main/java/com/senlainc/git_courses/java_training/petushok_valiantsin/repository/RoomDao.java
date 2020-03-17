package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryType;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;

import java.util.List;

@DependencyClass
@DependencyPrimary
public class RoomDao extends AbstractDao<Room, Long> implements IRoomDao {

    private static final String ERROR = "Error during connection to Database. Check query.";

    @Override
    public List<Room> readAllFree() {
        try {
            return entityManager.createQuery(QueryDao.ROOM.getQuery(QueryType.READ_ALL_FREE)).getResultList();
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public Long readFreeSize() {
        try {
            return (Long) entityManager.createQuery(QueryDao.ROOM.getQuery(QueryType.FREE_SIZE)).getSingleResult();
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public Room readByNumber(Integer number) {
        try {
            return (Room) entityManager.createQuery(QueryDao.ROOM.getQuery(QueryType.READ_BY_NUMBER)).getSingleResult();
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
    }

}
