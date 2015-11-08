package com.epam.dao.H2;

import com.epam.dao.DaoException;
import com.epam.dao.DaoFactory;
import com.epam.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class H2DaoFactory extends DaoFactory {
    private static final Logger log = LoggerFactory.getLogger(H2DaoFactory.class);
    private static final ResourceBundle resource = ResourceBundle.getBundle("database");
    private ConnectionPool pool;

    /**
     * constructor in which is initialized ConnectionPool with 10 connections size
     */
    public H2DaoFactory() {
        try {
            pool = new ConnectionPool();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void releaseConnections() {
        try {
            pool.closeConnections();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * @return H2DaoManager with initialized Connection
     */
    public H2DaoManager getDaoManager() {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            log.info("has been taken new DaoManager and " +
                    "transaction isolation was set to {}", connection.getTransactionIsolation());
        } catch (SQLException e) {
        }
        return new H2DaoManager(connection);
    }

    public void setConnectionPool(ConnectionPool pool) {
        this.pool = pool;
    }
}
