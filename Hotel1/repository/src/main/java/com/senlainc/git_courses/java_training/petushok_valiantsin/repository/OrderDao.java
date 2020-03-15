package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.OrderStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryType;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class OrderDao implements IOrderDao {
    private static final String ERROR = "Error during connection to Database. Check query.";
    @DependencyComponent
    private ConnectionManager connectionManager;

    @Override
    public void create(Order order) {
        final String QUERY = QueryDao.ORDER.getQuery(QueryType.CREATE);
        try (final PreparedStatement statement = connectionManager.getConnection().prepareStatement(QUERY)) {
            statement.setTimestamp(1, Timestamp.valueOf(order.getOrderDate()));
            statement.setInt(2, order.getGuest().getId());
            statement.setInt(3, order.getRoom().getId());
            statement.setDate(4, Date.valueOf(order.getStartDate()));
            statement.setDate(5, Date.valueOf(order.getEndDate()));
            statement.setString(6, order.getStatus().name());
            statement.setDouble(7, order.getPrice());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public void delete(Integer index) {
        final String QUERY = QueryDao.ORDER.getQuery(QueryType.DELETE);
        try (final PreparedStatement statement = connectionManager.getConnection().prepareStatement(QUERY)) {
            statement.setInt(1, index);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public void update(Order order) {
        final String QUERY = QueryDao.ORDER.getQuery(QueryType.UPDATE);
        try (final PreparedStatement statement = connectionManager.getConnection().prepareStatement(QUERY)) {
            statement.setInt(1, order.getGuest().getId());
            statement.setInt(2, order.getRoom().getId());
            statement.setDate(3, Date.valueOf(order.getEndDate()));
            statement.setString(4, order.getStatus().name());
            statement.setDouble(5, order.getPrice());
            statement.setInt(6, order.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public void update(Order order, Attendance attendance) {
        final String QUERY = QueryDao.ORDER.getQuery(QueryType.ADD_ORDER_ATTENDANCE);
        try (final PreparedStatement statement = connectionManager.getConnection().prepareStatement(QUERY)) {
            statement.setInt(1, order.getId());
            statement.setInt(2, attendance.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public List<Order> readAll() {
        final String QUERY = QueryDao.ORDER.getQuery(QueryType.READ_ALL);
        final List<Order> orderList = new ArrayList<>();
        try (final PreparedStatement statement = connectionManager.getConnection().prepareStatement(QUERY)) {
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                orderList.add(createOrderFromQuery(result));
            }
            result.close();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
        return orderList;
    }

    @Override
    public List<Room> readLastRoom(Integer index) {
        final String QUERY = QueryDao.ORDER.getQuery(QueryType.READ_LAST_ROOM);
        final List<Room> roomList = new ArrayList<>();
        try (final PreparedStatement statement = connectionManager.getConnection().prepareStatement(QUERY)) {
            statement.setInt(1, index);
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
        final String QUERY = QueryDao.ORDER.getQuery(QueryType.READ_AFTER_DATE);
        final List<Room> roomList = new ArrayList<>();
        try (final PreparedStatement statement = connectionManager.getConnection().prepareStatement(QUERY)) {
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
        final Room room = new Room(result.getInt("number"), result.getString("classification"), result.getShort("room_number")
                , result.getShort("capacity"), RoomStatus.valueOf(result.getString("status")), result.getDouble("price"));
        room.setId(result.getInt("id"));
        return room;
    }

    @Override
    public Order read(Integer index) {
        final String QUERY = QueryDao.ORDER.getQuery(QueryType.READ);
        try (final PreparedStatement statement = connectionManager.getConnection().prepareStatement(QUERY)) {
            statement.setInt(1, index);
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                final Order order = createOrderFromQuery(result);
                orderAttendance(order);
                result.close();
                return order;
            }
            throw new ElementNotFoundException();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
    }

    private Order createOrderFromQuery(ResultSet result) throws SQLException {
        final Room room = new Room(result.getInt("number"), result.getString("classification"), result.getShort("room_number")
                , result.getShort("capacity"), RoomStatus.valueOf(result.getString(14)), result.getDouble(15));
        room.setId(result.getInt(9));
        final Guest guest = new Guest(result.getString("first_name"), result.getString("second_name"), result.getDate("birthday").toLocalDate());
        guest.setId(result.getInt(16));
        final Order order = new Order(result.getTimestamp("order_date").toLocalDateTime(), guest, room, result.getDate("start_date").toLocalDate()
                , result.getDate("end_date").toLocalDate(), OrderStatus.valueOf(result.getString(7)), result.getDouble(8));
        order.setId(result.getInt(1));
        orderAttendance(order);
        return order;
    }

    private void orderAttendance(Order order) throws SQLException {
        final List<Attendance> attendanceList = new ArrayList<>();
        final String QUERY = QueryDao.ORDER.getQuery(QueryType.READ_ORDER_ATTENDANCE);
        try (final PreparedStatement statement = connectionManager.getConnection().prepareStatement(QUERY)) {
            statement.setInt(1, order.getId());
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                final Attendance attendance = new Attendance(result.getString("name"), result.getString("section"), result.getDouble("price"));
                attendance.setId(result.getInt("id"));
                attendanceList.add(attendance);
            }
            result.close();
        }
        order.setAttendanceIndex(attendanceList);
    }
}
