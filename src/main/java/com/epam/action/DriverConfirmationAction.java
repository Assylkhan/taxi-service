package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Driver;
import com.epam.entity.Order;
import com.epam.entity.Order.OrderStatus;
import com.epam.service.DriverService;
import com.epam.service.OrderService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DriverConfirmationAction implements Action {
    private static Logger log = LoggerFactory.getLogger(StartFlightAction.class);
    ActionResult accepted = new ActionResult("receivedOrders", true);
    ActionResult declined = new ActionResult("receivedOrders", true);
    private Driver driver;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        DaoFactory daoFactory = (DaoFactory) servletContext.getAttribute("daoFactory");
        String act = req.getParameter("act");
        driver = (Driver) req.getSession().getAttribute("driver");
//        DriverService driverService = new DriverService(daoFactory);
        OrderService orderService = new OrderService(daoFactory);
        Order order = createOrderBean(req);
        if (act.equals("accept")) {
            order.setStatus(OrderStatus.ACCEPTED);
            orderService.update(order);
            driver.setCurrentOrder(order);
            log.info("accepted {0}-{1} order by driver {2} received at {3}",
                    order.getPickupLocation(), order.getDropOffLocation(), driver.getLogin(),
                    order.getReceivedTime());
            req.getSession().setAttribute("driver", driver);
            return accepted;
        } else {
            order.setStatus(OrderStatus.NOT_SERVED);
            order.setDriver(null);
            orderService.update(order);
            log.info("declined {0}-{1} order by driver {2} at {3}",
                    order.getPickupLocation(), order.getDropOffLocation(), driver.getLogin(),
                    order.getReceivedTime());
            req.getSession().setAttribute("driver", driver);
            return declined;
        }
    }

    private Order createOrderBean(HttpServletRequest req) {
        Long orderId = Long.valueOf(req.getParameter("orderId"));
        Order order = new Order();
        order.setId(orderId);
        order.setDriver(driver);
        return order;
    }
}
