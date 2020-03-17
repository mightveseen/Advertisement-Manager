package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection;

import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration.ConfigController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration.annotation.ConfigProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

@ConfigClass
public final class ConnectionManager {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionManager.class);
    @ConfigProperty(configName = "Base")
    private static String URL;
    @ConfigProperty(configName = "Base")
    private static String USER;
    @ConfigProperty(configName = "Base")
    private static String PASSWORD;
    private Connection connection;

    private ConnectionManager() {
        ConfigController.getInstance().setConfig(ConnectionManager.class);
//        try {
//            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            setAutoCommit(false);
//            LOGGER.info("Open Database connection");
//        } catch (SQLException e) {
//            LOGGER.warn(e.getMessage(), e);
//        }
    }

    public void setAutoCommit(Boolean condition) {
        try {
            this.connection.setAutoCommit(condition);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public void closeConnection() {
        try {
            this.connection.close();
            LOGGER.info("Close Database connection");
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void commit() {
        try {
            this.connection.commit();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public void rollback() {
        try {
            this.connection.rollback();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public Savepoint setSavepoint(String name) {
        try {
            return this.connection.setSavepoint(name);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return null;
    }

    public void releaseSavepoint(Savepoint savepoint) {
        try {
            this.connection.releaseSavepoint(savepoint);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }
}
