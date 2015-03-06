package com.epam.service;

import com.epam.dao.*;
import com.epam.entity.Client;
import com.epam.entity.Driver;
import com.epam.entity.Order;

import java.util.List;

public class ClientService {
    private DaoFactory factory;

    public ClientService(DaoFactory factory) {
        this.factory = factory;
    }

    public Client findById(Long id) {
        Client client = null;
        try (DaoManager daoManager = factory.getDaoManager()) {
            ClientDao clientDao = daoManager.getClientDao();
            client = clientDao.findById(id);
            OrderDao orderDao = daoManager.getOrderDao();
            List<Order> orders = orderDao.findAll();
            if (orders != null && orders.size() > 0) {
                DriverDao driverDao = daoManager.getDriverDao();
                for (Order order : orders) {
                    Driver driver = driverDao.findByOrderId(order.getId());
                    order.setDriver(driver);
                }
                client.setOrders(orders);
            }
        }
        return client;
    }

    public Client insert(Client client) {
        try (DaoManager daoManager = factory.getDaoManager()) {
            ClientDao clientDao = daoManager.getClientDao();
            Client insertedClient = clientDao.insert(client);
            return insertedClient;
        }
    }

    public Client findByCredentials(String login, String password) {
        try (DaoManager daoManager = factory.getDaoManager()) {
            ClientDao clientDao = daoManager.getClientDao();
            Client client = clientDao.findByCredentials(login, password);
            return client;
        }
    }

    public void update(Client client) {
        try (DaoManager daoManager = factory.getDaoManager()) {
            ClientDao clientDao = daoManager.getClientDao();
            clientDao.update(client);
        }
    }
}