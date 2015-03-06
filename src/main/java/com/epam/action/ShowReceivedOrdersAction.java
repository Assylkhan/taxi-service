package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Driver;
import com.epam.entity.Order;
import com.epam.service.OrderService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowReceivedOrdersAction implements Action {
    ActionResult result = new ActionResult("receivedOrders");


    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        DaoFactory daoFactory = (DaoFactory) servletContext.getAttribute("daoFactory");
        Driver driver = (Driver)req.getSession().getAttribute("driver");
        OrderService orderService = new OrderService(daoFactory);
        List<Order> orders = orderService.findNotCompletedByDriverId(driver.getId());
        driver.setOrders(orders);
        req.getSession().setAttribute("driver", driver);
        return result;
    }
}
