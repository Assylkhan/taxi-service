package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.Dispatcher;
import com.epam.entity.Driver;
import com.epam.entity.Order;
import com.epam.service.DriverService;
import com.epam.service.OrderService;
import com.epam.validation.InputValidator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;


public class ChooseDriverAction implements Action {
    ActionResult result = new ActionResult("orderServing", true);
    ActionResult failed = new ActionResult("orderServing");
    private static final Logger log = LoggerFactory.getLogger(ChooseDriverAction.class);
    private static String cost;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        if (validateParameters(req) == false) {
            req.setAttribute("flash.error", "dispatcher.message.cannotBeBlank");
            return result;
        }
        Order order = createOrderBean(req);
        if (order == null) {
            req.setAttribute("flash.error", "dispatcher.message.alreadyGivenToDriver");
            return result;
        }
        OrderService orderService = new OrderService(daoFactory);
        orderService.entrustToDriver(order);
        log.info("{}-{} order entrusted to {} driver received at {}",
                order.getPickupLocation(), order.getDropOffLocation(), order.getDriver().getLogin(),
                order.getReceivedTime());
        req.setAttribute("flash.chosenDriver", "dispatcher.message.taskWasGiven");
        return result;
    }

    private Order createOrderBean(HttpServletRequest req) {
        Long driverId = Long.valueOf(req.getParameter("driverId"));
        Long orderId = Long.valueOf(req.getParameter("orderId"));
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        OrderService orderService = new OrderService(daoFactory);
        DriverService driverService = new DriverService(daoFactory);
        Driver driver = driverService.findById(driverId);
        driver.setId(driverId);
        Order order = orderService.findById(orderId);
        order.setCost(new BigDecimal(cost));
        order.setDriver(driver);
        order.setStatus(Order.OrderStatus.NOT_SERVED);
        Dispatcher dispatcher = (Dispatcher) req.getSession().getAttribute("dispatcher");
        order.setDispatcher(dispatcher);
        return order;
    }

    private boolean validateParameters(HttpServletRequest req) {
        String driverId = req.getParameter("driverId");
        String orderId = req.getParameter("orderId");
        if (!validateCost(req) || driverId == null || driverId.isEmpty()
                || orderId == null || orderId.isEmpty())
            return false;
        return true;
    }

    private boolean validateCost(HttpServletRequest req) {
        cost = req.getParameter("cost");
        return (InputValidator.money(cost));
    }
}
