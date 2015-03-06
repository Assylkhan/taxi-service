package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.Driver;
import com.epam.entity.Order;
import com.epam.entity.Order.OrderStatus;
import com.epam.service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class StartFlightAction implements Action {
    private static Logger log = LoggerFactory.getLogger(StartFlightAction.class);
    ActionResult success = new ActionResult("currentOrder", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        Driver driver = (Driver) req.getSession().getAttribute("driver");
        DriverService driverService = new DriverService(daoFactory);
        Order currentOrder = driver.getCurrentOrder();
        currentOrder.setStatus(OrderStatus.IN_PROCESS);
        driverService.updateOrderByDriver(driver);
        log.info("driver {0} started order {1} - {2}", driver.getLogin(),
                currentOrder.getPickupLocation(), currentOrder.getDropOffLocation());
        req.getSession().setAttribute("driver", driver);
        return success;
    }
}
