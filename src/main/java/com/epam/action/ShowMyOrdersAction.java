package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Client;
import com.epam.entity.Order;
import com.epam.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowMyOrdersAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        OrderService orderService = new OrderService(daoFactory);
        Client client = (Client) req.getSession().getAttribute("client");
        List<Order> orders = orderService.findNotCompletedByClientId(client.getId());
        req.setAttribute("orders", orders);
        req.setAttribute("myOrders", "active");
        return new ActionResult("clientServing");
    }
}
