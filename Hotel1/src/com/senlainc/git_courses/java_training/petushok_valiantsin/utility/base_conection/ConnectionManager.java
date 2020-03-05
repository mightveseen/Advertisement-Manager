package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.ConfigController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigProperty;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.logging.Level;
import java.util.logging.Logger;

@ConfigClass
@DependencyClass
public class ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());
    @ConfigProperty(configName = "Base")
    private static String URL;
    @ConfigProperty(configName = "Base")
    private static String USER;
    @ConfigProperty(configName = "Base")
    private static String PASSWORD;
    private Connection connection;

    private ConnectionManager() {
        ConfigController.getInstance().setConfig(ConnectionManager.class);
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public void setAutoCommit(Boolean condition) {
        try {
            this.connection.setAutoCommit(condition);
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

    public void commit() {
        try {
            this.connection.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public void rollback() {
        try {
            this.connection.rollback();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public Savepoint setSavepoint(String name) {
        try {
            return this.connection.setSavepoint(name);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return null;
    }

    public void releaseSavepoint(Savepoint savepoint) {
        try {
            this.connection.releaseSavepoint(savepoint);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public PreparedStatement getStatment(String sqlQuery) throws SQLException {
        return connection.prepareStatement(sqlQuery);
    }
}
