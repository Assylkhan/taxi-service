package com.epam.dao;

import com.epam.entity.Dispatcher;

public interface DispatcherDao extends Dao<Dispatcher> {
    /**
     *
     * @param login
     * @param password
     * @return Dispatcher with initialized fields
     * @throws DaoException
     */
    public Dispatcher findByCredentials(String login, String password) throws DaoException;
    public int count() throws DaoException;

    void isExistsNationalId(String nationalId) throws DaoException;
}
