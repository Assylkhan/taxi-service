package com.epam.dao;

import com.epam.entity.Admin;

public interface AdminDao extends Dao<Admin> {
    public Admin findByCredentials(String login, String password) throws DaoException;
}
