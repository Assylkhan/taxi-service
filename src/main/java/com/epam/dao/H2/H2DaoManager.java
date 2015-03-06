package com.epam.dao.H2;

import com.epam.dao.*;

import java.sql.Connection;
import java.sql.SQLException;

public class H2DaoManager implements DaoManager {
    private Connection connection = null;

    public H2DaoManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void beginTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void commit() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void rollback() throws DaoException {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void closeQuietly() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ignore) {
        }
    }

    @Override
    public ClientDao getClientDao() throws DaoException {
        if (connection == null) throw new DaoException("no connection");
        return new H2ClientDao(connection);
    }

    @Override
    public DriverDao getDriverDao() throws DaoException {
        if (connection == null) throw new DaoException("no connection");
        return new H2DriverDao(connection);
    }

    @Override
    public DispatcherDao getDispatcherDao() throws DaoException {
        if (connection == null) throw new DaoException("no connection");
        return new H2DispatcherDao(connection);
    }

    @Override
    public CommentDao getCommentDao() throws DaoException {
        if (connection == null) throw new DaoException("no connection");
        return new H2CommentDao(connection);
    }

    @Override
    public AdminDao getAdminDao() throws DaoException {
        if (connection == null) throw new DaoException("no connection");
        return new H2AdminDao(connection);
    }

    @Override
    public AnnouncementDao getAnnouncementDao() {
        if (connection == null) throw new DaoException("no connection");
        return new H2AnnouncementDao(connection);
    }

    @Override
    public OrderDao getOrderDao() throws DaoException {
        if (connection == null) throw new DaoException("no connection");
        return new H2OrderDao(connection);
    }

    /*public <T> T getDao(T dao){
        if (connection == null) throw new DaoException("no connection");
        try {
            dao = (T) Class.forName("H2" + dao).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dao;
    }*/
}
