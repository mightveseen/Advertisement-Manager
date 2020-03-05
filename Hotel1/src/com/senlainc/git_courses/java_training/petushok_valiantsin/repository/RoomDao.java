package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryType;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class RoomDao implements IRoomDao {
    @DependencyComponent
    private ConnectionManager connectionManager;

    @Override
    public void create(Room room) {
        final String QUERY = QueryDao.ROOM.getQuery(QueryType.CREATE);
        try (PreparedStatement statement = connectionManager.getStatment(QUERY)) {
            statement.setInt(1, room.getNumber());
            statement.setString(2, room.getClassification());
            statement.setShort(3, room.getRoomNumber());
            statement.setShort(4, room.getCapacity());
            statement.setString(5, room.getStatus().name());
            statement.setDouble(6, room.getPrice());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer index) {
        final String QUERY = QueryDao.ROOM.getQuery(QueryType.DELETE);
        try (PreparedStatement statement = connectionManager.getStatment(QUERY)) {
            statement.setInt(1, index);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Room room) {
        final String QUERY = QueryDao.ROOM.getQuery(QueryType.UPDATE);
        try (PreparedStatement statement = connectionManager.getStatment(QUERY)) {
            statement.setInt(1, room.getNumber());
            statement.setString(2, room.getClassification());
            statement.setShort(3, room.getRoomNumber());
            statement.setShort(4, room.getCapacity());
            statement.setString(5, room.getStatus().name());
            statement.setDouble(6, room.getPrice());
            statement.setInt(7, room.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Room> readAll() {
        final String QUERY = QueryDao.ROOM.getQuery(QueryType.READ_ALL);
        final List<Room> roomList = new ArrayList<>();
        try (PreparedStatement statement = connectionManager.getStatment(QUERY)) {
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                roomList.add(createFromQuery(result));
            }
            result.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return roomList;
    }

    @Override
    public List<Room> readAllFree() {
        final String QUERY = QueryDao.ROOM.getQuery(QueryType.READ_ALL_FREE);
        final List<Room> roomList = new ArrayList<>();
        try (PreparedStatement statement = connectionManager.getStatment(QUERY)) {
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                roomList.add(createFromQuery(result));
            }
            result.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return roomList;
    }

    @Override
    public Integer readFreeSize() {
        final String QUERY = QueryDao.ROOM.getQuery(QueryType.FREE_SIZE);
        try (PreparedStatement statement = connectionManager.getStatment(QUERY)) {
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                final Integer size = result.getInt(1);
                result.close();
                return size;
            }
            throw new ElementNotFoundException();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Room read(Integer index) {
        final String QUERY = QueryDao.ROOM.getQuery(QueryType.READ);
        try (PreparedStatement statement = connectionManager.getStatment(QUERY)) {
            statement.setInt(1, index);
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                final Room room = createFromQuery(result);
                result.close();
                return room;
            }
            throw new ElementNotFoundException();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Room readByNumber(Integer number) {
        final String QUERY = QueryDao.ROOM.getQuery(QueryType.READ_BY_NUMBER);
        try (PreparedStatement statement = connectionManager.getStatment(QUERY)) {
            statement.setInt(1, number);
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                final Room room = createFromQuery(result);
                result.close();
                return room;
            }
            return null;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Room createFromQuery(ResultSet result) throws SQLException {
        final Room room = new Room(result.getInt(2), result.getString(3)
                , result.getShort(4), result.getShort(5)
                , RoomStatus.valueOf(result.getString(6)), result.getDouble(7));
        room.setId(result.getInt(1));
        return room;
    }
}
