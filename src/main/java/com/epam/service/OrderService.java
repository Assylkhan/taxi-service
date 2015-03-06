package com.epam.service;

import com.epam.dao.*;
import com.epam.entity.Client;
import com.epam.entity.Driver;
import com.epam.entity.Order;

import java.time.YearMonth;
import java.util.List;

public class OrderService {
    private DaoFactory daoFactory;

    public OrderService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Order insert(Order order) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            Order insertedOrder = orderDao.insert(order);
            return insertedOrder;
        }
    }

    public List<Order> findAll() {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            List<Order> orders = orderDao.findAll();
            fillOrdersByClientAndDriver(orders, daoManager);
            return orders;
        }
    }

    public List<Order> findNotCompleted() {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            List<Order> orders = orderDao.findNotCompleted();
            if (orders != null && orders.size() > 0) {
                ClientDao clientDao = daoManager.getClientDao();
                orders.forEach(order -> {
                    fillOrderByClient(order, clientDao);
                });
            }
            return orders;
        }
    }

    public void update(Order order) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            orderDao.update(order);
        }
    }

    /**
     * set driverId in order entry and update driver state to notAvailable
     *
     * @param order
     */
    public void entrustToDriver(Order order) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            orderDao.update(order);
        }
    }

    /**
     * @param id
     * @return not completed orders list of given driver
     */
    public List<Order> findNotCompletedByDriverId(Long id) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            List<Order> orders = orderDao.findNotCompletedByDriverId(id);
            fillOrdersByClientAndDriver(orders, daoManager);
            return orders;
        }
    }

    public List<Order> findNotCompletedByClientId(Long id) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            List<Order> orders = orderDao.findNotCompletedByClientId(id);
            if (orders.size() == 0) return null;
            fillOrdersByClientAndDriver(orders, daoManager);
            return orders;
        }
    }

    /**
     * @param id
     * @return orders by status expecting for client, to notify client that driver is expecting him
     */
    public List<Order> findExpectingByClientId(Long id) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            List<Order> orders = orderDao.findExpectingByClientId(id);
            if (orders.size() == 0) return null;
            fillOrdersByClientAndDriver(orders, daoManager);
            return orders;
        }
    }

    public Order findById(Long id) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            Order order = orderDao.findById(id);
            ClientDao clientDao = daoManager.getClientDao();
            fillOrderByClient(order, clientDao);
            return order;
        }
    }

    public void deleteById(Long id) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            orderDao.deleteById(id);
        }
    }

    /**
     * @param page
     * @return page of given number entries of orders
     */
    public Page<Order> paginateAll(Page<Order> page) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            page = orderDao.paginateAll(page);
            return page;
        }
    }

    /**
     * @param page
     * @return page of not served yet orders
     */
    public Page<Order> paginateNotServed(Page<Order> page) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            page = orderDao.paginateNotServed(page);
            ClientDao clientDao = daoManager.getClientDao();
            for (Order order : page.getItems()) {
                fillOrderByClient(order, clientDao);
            }
            return page;
        }
    }

    /**
     * get taken up order by driver
     *
     * @param id
     * @return taken up order by driver
     */
    public Order findTakenUpByDriverId(Long id) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            Order order = orderDao.findTakenUpByDriverId(id);
            DriverDao driverDao = daoManager.getDriverDao();
            ClientDao clientDao = daoManager.getClientDao();
            fillOrderByClientAndDriver(order, clientDao, driverDao);
            return order;
        }
    }

    /**
     * @param id
     * @return worked months of given dispatcher
     */
    public List<YearMonth> findMonthsByDispatcherId(Long id) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            List<YearMonth> months = orderDao.findMonthsByDispatcherId(id);
            return months;
        }
    }

    /**
     * @param id
     * @return worked months of given driver
     */
    public List<YearMonth> findMonthsByDriverId(Long id) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            List<YearMonth> months = orderDao.findMonthsByDriverId(id);
            return months;
        }
    }

    public List<Order> findByDriverId(Long id) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            List<Order> orders = orderDao.findByDriverId(id);
            return orders;
        }
    }

    public List<Order> findByDispatcherId(Long id) {
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            OrderDao orderDao = daoManager.getOrderDao();
            List<Order> orders = orderDao.findByDispatcherId(id);
            return orders;
        }
    }

    /**
     * fill orders list by client and driver objects
     *
     * @param orders
     * @param daoManager
     */
    private void fillOrdersByClientAndDriver(List<Order> orders, DaoManager daoManager) {
        if (orders == null || orders.size() == 0) return;
        DriverDao driverDao = daoManager.getDriverDao();
        ClientDao clientDao = daoManager.getClientDao();
        for (Order order : orders) {
            fillOrderByClientAndDriver(order, clientDao, driverDao);
        }
    }

    /**
     * fill given order by client and driver object
     *
     * @param order
     * @param clientDao
     * @param driverDao
     */
    private void fillOrderByClientAndDriver(Order order, ClientDao clientDao, DriverDao driverDao) {
        fillOrderByClient(order, clientDao);
        fillOrderByDriver(order, driverDao);
    }

    /**
     * fill given order by client
     *
     * @param order
     * @param clientDao
     */
    private void fillOrderByClient(Order order, ClientDao clientDao) {
        if (order == null) return;
        Client client = clientDao.findByOrderId(order.getId());
        order.setClient(client);
    }

    /**
     * fill given order by driver
     *
     * @param order
     * @param driverDao
     */
    private void fillOrderByDriver(Order order, DriverDao driverDao) {
        if (order == null) return;
        Driver driver = driverDao.findByOrderId(order.getId());
        order.setDriver(driver);
    }
}
