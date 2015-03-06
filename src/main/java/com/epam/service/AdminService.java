package com.epam.service;

import com.epam.dao.AdminDao;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Admin;

public class AdminService {
    private DaoFactory daoFactory;

    public AdminService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Admin findByCredentials(String login, String password) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            AdminDao adminDao = daoManager.getAdminDao();
            Admin admin = adminDao.findByCredentials(login, password);
            return admin;
        }
    }

    public Admin insert(Admin admin) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            AdminDao adminDao = daoManager.getAdminDao();
            Admin insertedAdmin = adminDao.insert(admin);
            return insertedAdmin;
        }
    }
}
