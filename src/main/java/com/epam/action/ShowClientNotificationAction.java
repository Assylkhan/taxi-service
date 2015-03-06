package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Client;
import com.epam.entity.Order;
import com.epam.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowClientNotificationAction implements Action {
    ActionResult result = new ActionResult("clientServing");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Client client = (Client) req.getSession().getAttribute("client");
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        OrderService orderService = new OrderService(daoFactory);

        List<Order> orders = orderService.findExpectingByClientId(client.getId());
        req.setAttribute("notifyOrders", orders);
        /**
         * tabPanel will be changed to notifications
         */
        req.setAttribute("notifications", "active");
        return result;
    }
}
