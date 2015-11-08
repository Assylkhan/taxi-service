package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Driver;
import com.epam.entity.Order;
import com.epam.service.DriverService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TakeUpFlightAction implements Action {

    ActionResult success = new ActionResult("currentOrder", true);
    private static final Logger log = LoggerFactory.getLogger(TakeUpFlightAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        Driver driver = (Driver) req.getSession().getAttribute("driver");
        DriverService driverService = new DriverService(daoFactory);
        Order currentOrder = driver.getCurrentOrder();
        currentOrder.setStatus(Order.OrderStatus.TAKEN_UP);
        driverService.updateOrderByDriver(driver);
        log.info("taken up flight by driver {} started order {} - {}", driver.getLogin(),
        currentOrder.getPickupLocation(), currentOrder.getDropOffLocation());
        req.getSession().setAttribute("driver", driver);
        return success;
    }
}
