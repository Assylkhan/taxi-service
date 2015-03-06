package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Driver;
import com.epam.entity.Order;
import com.epam.service.DriverService;
import com.epam.service.OrderService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DriverOrderServeAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(DriverOrderServeAction.class);
    ActionResult result = new ActionResult("currentOrder", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        DaoFactory daoFactory = (DaoFactory) servletContext.getAttribute("daoFactory");
        Driver driver = (Driver) req.getSession().getAttribute("driver");
        OrderService orderService = new OrderService(daoFactory);
        Long orderId = Long.valueOf(req.getParameter("orderId"));
        Order order = orderService.findById(orderId);
        /**
         * driver have many received orders, and one of their become current order to serve it.
         */
        driver.setCurrentOrder(order);
        driver.setAvailable(false);
        DriverService driverService = new DriverService(daoFactory);
        driverService.updateState(driver);
        log.info("driver {0} has taken up order {1}-{2}, at {3}",
                driver.getLogin(), order.getPickupLocation(), order.getDropOffLocation(),
                order.getReceivedTime());
        req.getSession().setAttribute("driver", driver);
        return result;
    }
}
