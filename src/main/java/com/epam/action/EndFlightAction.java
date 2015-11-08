package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.Client;
import com.epam.entity.Driver;
import com.epam.entity.Order;
import com.epam.entity.Order.OrderStatus;
import com.epam.service.ClientService;
import com.epam.service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.service.OrderService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.math.BigDecimal;

public class EndFlightAction implements Action {
    ActionResult success = new ActionResult("receivedOrders", true);
    private static final Logger log = LoggerFactory.getLogger(EndFlightAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Driver driver = (Driver) req.getSession().getAttribute("driver");
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        DriverService driverService = new DriverService(daoFactory);
        Order currentOrder = driver.getCurrentOrder();
        Client client = currentOrder.getClient();
        if (client != null) {
            /**
             * when flight are ended client's bonus will increase to 50 tenge
             */
            BigDecimal bonus = client.getBonus().add(new BigDecimal(50));
            ClientService clientService = new ClientService(daoFactory);
            client.setBonus(bonus);
            /**
             * when client have been served, if client's bonus equals or greater than 500, assumed he used
             * bonus and 500 tenge will be removed from his bonus
             */
            if (bonus.equals(500) || bonus.compareTo(new BigDecimal(500)) == 1) {
                client.setBonus(bonus.subtract(new BigDecimal(500)));
            }
            clientService.update(client);
        }
        currentOrder.setStatus(OrderStatus.COMPLETED);
        driverService.updateOrderByDriver(driver);
        log.info("driver {} has finished order {}-{}, that received at {}",
                driver.getLogin(), currentOrder.getPickupLocation(),
                currentOrder.getDropOffLocation(),
                currentOrder.getReceivedTime());
        driver.setCurrentOrder(null);
        req.getSession().setAttribute("driver", driver);
        return success;
    }
}
