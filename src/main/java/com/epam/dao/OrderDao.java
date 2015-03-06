package com.epam.dao;

import com.epam.entity.Driver;
import com.epam.entity.Order;

import java.time.YearMonth;
import java.util.List;

public interface OrderDao extends Dao<Order> {
    /**
     *
     * @param id
     * @return Order by Driver id where order not completed yet
     * @throws DaoException
     */
    public List<Order> findNotCompletedByDriverId(Long id) throws DaoException;

    /**
     *
     * @param id
     * @return not completed order by given client id
     * @throws DaoException
     */
    public List<Order> findNotCompletedByClientId(Long id) throws DaoException;

    /**
     *
     * @return not completed orders collection
     * @throws DaoException
     */
    public List<Order> findNotCompleted() throws DaoException;

    /**
     * removes entry of given driver
     * @param driver
     * @throws DaoException
     */
    public void removeDriver(Driver driver) throws DaoException;

    List<Order> findExpectingByClientId(Long id) throws DaoException;

    Page<Order> paginateAll(Page<Order> page) throws DaoException;

    Page<Order> paginateNotServed(Page<Order> page) throws DaoException;

    Order findTakenUpByDriverId(Long id) throws DaoException;

    public List<YearMonth> findMonthsByDispatcherId(Long id) throws DaoException;

    public List<YearMonth> findMonthsByDriverId(Long id) throws DaoException;

    public List<Order> findByDriverId(Long id) throws DaoException;

    public List<Order> findByDispatcherId(Long id) throws DaoException;
}
