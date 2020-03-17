package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryType;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class OrderDao extends AbstractDao<Order, Long> implements IOrderDao {

    private static final String ERROR = "Error during connection to Database. Check query.";
    @DependencyComponent
    private ConnectionManager connectionManager;

    public OrderDao() {
        super(Order.class);
    }

    @Override
    public void update(Order order, Attendance attendance) {
        final String query = QueryDao.ORDER.getQuery(QueryType.ADD_ORDER_ATTENDANCE);
        try (PreparedStatement statement = connectionManager.getConnection().prepareStatement(query)) {
            statement.setInt(1, order.getId());
            statement.setInt(2, (int) attendance.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public List<Room> readLastRoom(Long index) {
        final String query = QueryDao.ORDER.getQuery(QueryType.READ_LAST_ROOM);
        final List<Room> roomList = new ArrayList<>();
        try (PreparedStatement statement = connectionManager.getConnection().prepareStatement(query)) {
//            statement.setInt(1, (int) index);
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                roomList.add(createRoomFromQuery(result));
            }
            result.close();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
        return roomList;
    }

    @Override
    public List<Room> readAfterDate(LocalDate date) {
        final String query = QueryDao.ORDER.getQuery(QueryType.READ_AFTER_DATE);
        final List<Room> roomList = new ArrayList<>();
        try (PreparedStatement statement = connectionManager.getConnection().prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(date));
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                roomList.add(createRoomFromQuery(result));
            }
            result.close();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
        return roomList;
    }

    private Room createRoomFromQuery(ResultSet result) throws SQLException {
        final Room room = new Room(result.getInt("number"), result.getString("classification"), result.getShort("room_number"),
                result.getShort("capacity"), RoomStatus.valueOf(result.getString("status")), result.getDouble("price"));
        room.setId(result.getInt("id"));
        return room;
    }
}
