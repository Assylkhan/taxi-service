package com.epam.action;


import com.epam.dao.DaoFactory;
import com.epam.entity.Driver;
import com.epam.entity.Order.OrderStatus;
import com.epam.service.DriverService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotifyClientAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(NotifyClientAction.class);
    ActionResult success = new ActionResult("currentOrder", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Driver driver = (Driver) req.getSession().getAttribute("driver");
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        DriverService driverService = new DriverService(daoFactory);
        driver.getCurrentOrder().setStatus(OrderStatus.CLIENT_EXPECTING);
        driverService.updateOrderByDriver(driver);
        log.info("driver {0} is expecting client", driver.getLogin());
        req.getSession().setAttribute("driver", driver);
        return success;
    }
}
