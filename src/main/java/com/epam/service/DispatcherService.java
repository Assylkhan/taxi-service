package com.epam.service;

import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.dao.DispatcherDao;
import com.epam.dao.OrderDao;
import com.epam.entity.Dispatcher;

import java.util.List;

public class DispatcherService {
    private DaoFactory factory;

    public DispatcherService(DaoFactory factory) {
        this.factory = factory;
    }

    public Dispatcher insert(Dispatcher dispatcher) {
        try (DaoManager daoManager = factory.getDaoManager()) {
            DispatcherDao dispatcherDao = daoManager.getDispatcherDao();
            dispatcherDao.isExistsNationalId(dispatcher.getNationalId());
            Dispatcher insertedDispatcher = dispatcherDao.insert(dispatcher);
            return insertedDispatcher;
        }
    }

    public Dispatcher findByCredentials(String login, String password) {
        try (DaoManager daoManager = factory.getDaoManager()) {
            DispatcherDao dispatcherDao = daoManager.getDispatcherDao();
            Dispatcher dispatcher = dispatcherDao.findByCredentials(login, password);
            return dispatcher;
        }
    }

    public List<Dispatcher> findAll() {
        try (DaoManager daoManager = factory.getDaoManager()) {
            DispatcherDao dispatcherDao = daoManager.getDispatcherDao();
            List<Dispatcher> dispatchers = dispatcherDao.findAll();
            return dispatchers;
        }
    }

    /**
     *
     * @return all dispatcher's count
     */
    public int count() {
        try (DaoManager daoManager = factory.getDaoManager()) {
            DispatcherDao dispatcherDao = daoManager.getDispatcherDao();
            int count = dispatcherDao.count();
            return count;
        }
    }

    public Dispatcher findById(Long id) {
        try (DaoManager daoManager = factory.getDaoManager()) {
            DispatcherDao dispatcherDao = daoManager.getDispatcherDao();
            Dispatcher dispatcher = dispatcherDao.findById(id);
            return dispatcher;
        }
    }
}
