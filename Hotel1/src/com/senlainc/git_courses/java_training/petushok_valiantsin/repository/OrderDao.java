package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.OrderStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QuaryDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QuaryType;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class OrderDao implements IOrderDao {
    @DependencyComponent
    private ConnectionManager connectionManager;

    @Override
    public Order create(Order order) {
        final String QUARY = QuaryDao.ORDER.getQuary(QuaryType.CREATE);
        try (PreparedStatement statement = connectionManager.getStatment(QUARY)) {
            statement.setTimestamp(1, Timestamp.valueOf(order.getOrderDate()));
            statement.setInt(2, order.getGuest().getId());
            statement.setInt(3, order.getRoom().getId());
            statement.setDate(4, Date.valueOf(order.getStartDate()));
            statement.setDate(5, Date.valueOf(order.getEndDate()));
            statement.setString(6, order.getStatus().name());
            statement.setDouble(7, order.getPrice());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    @Override
    public void delete(Integer index) {
        final String QUARY = QuaryDao.ORDER.getQuary(QuaryType.DELETE);
        try (PreparedStatement statement = connectionManager.getStatment(QUARY)) {
            statement.setInt(1, index);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Order order) {
        final String QUARY = QuaryDao.ORDER.getQuary(QuaryType.UPDATE);
        try (PreparedStatement statement = connectionManager.getStatment(QUARY)) {
            statement.setTimestamp(1, Timestamp.valueOf(order.getOrderDate()));
            statement.setInt(2, order.getGuest().getId());
            statement.setInt(3, order.getRoom().getId());
            statement.setDate(4, Date.valueOf(order.getStartDate()));
            statement.setDate(5, Date.valueOf(order.getEndDate()));
            statement.setString(6, order.getStatus().name());
            statement.setDouble(7, order.getPrice());
            statement.setInt(8, order.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> readAll() {
        final String QUARY = QuaryDao.ORDER.getQuary(QuaryType.READ_ALL);
        final List<Order> orderList = new ArrayList<>();
        try (PreparedStatement statement = connectionManager.getStatment(QUARY)) {
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                orderList.add(createFromQuary(result));
            }
            result.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return orderList;
    }

    @Override
    public Order read(Integer index) {
        final String QUARY = QuaryDao.ORDER.getQuary(QuaryType.READ);
        try (PreparedStatement statement = connectionManager.getStatment(QUARY)) {
            statement.setInt(1, index);
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                final Order order = createFromQuary(result);
                result.close();
                return order;
            }
            throw new ElementNotFoundException();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Order createFromQuary(ResultSet result) throws SQLException {
        final Room room = new Room(result.getInt(10), result.getString(11), result.getShort(12)
                , result.getShort(13), RoomStatus.valueOf(result.getString(14)), result.getDouble(15));
        room.setId(result.getInt(9));
        final Guest guest = new Guest(result.getString(17), result.getString(18), result.getDate(19).toLocalDate()
                , result.getString(20));
        guest.setId(result.getInt(16));
        final Order order = new Order(result.getTimestamp(2).toLocalDateTime(), guest, room, result.getDate(5).toLocalDate()
                , result.getDate(6).toLocalDate(), OrderStatus.valueOf(result.getString(7)), result.getDouble(8));
        order.setId(result.getInt(1));
        return order;
    }
}
