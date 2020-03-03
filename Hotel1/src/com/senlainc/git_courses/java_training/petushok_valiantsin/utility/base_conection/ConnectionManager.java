package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection;

import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@DependencyClass
public class ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());
    private Connection connection;

    private ConnectionManager() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelBase", "root", "root1234");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public PreparedStatement getStatment(String sqlQuery) throws SQLException {
        return connection.prepareStatement(sqlQuery);
    }

    public void closeStatment(PreparedStatement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
}
