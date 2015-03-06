package com.epam.service;

import com.epam.dao.*;
import com.epam.entity.*;

public class UserService {
    private DaoFactory factory;

    public UserService(DaoFactory factory) {
        this.factory = factory;
    }

    /**
     * @param login
     * @param password
     * @param role
     * @return user of given role from dao
     */
    public User findByCredentials(String login, String password, Role role) {
        try (DaoManager daoManager = factory.getDaoManager()) {
            switch (role) {
                case CLIENT:
                    ClientDao clientDao = daoManager.getClientDao();
                    Client client = clientDao.findByCredentials(login, password);
                    return client;
                case DISPATCHER:
                    DispatcherDao dispatcherDao = daoManager.getDispatcherDao();
                    Dispatcher dispatcher = dispatcherDao.findByCredentials(login, password);
                    return dispatcher;
                case ADMIN:
                    AdminDao adminDao = daoManager.getAdminDao();
                    Admin admin = adminDao.findByCredentials(login, password);
                    return admin;
                default:
                    DriverDao driverDao = daoManager.getDriverDao();
                    Driver driver = driverDao.findByCredentials(login, password);
                    return driver;
            }
        }
    }
}
