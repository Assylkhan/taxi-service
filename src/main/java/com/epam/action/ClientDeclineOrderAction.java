package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Client;
import com.epam.entity.Order;
import com.epam.service.ClientService;
import com.epam.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ClientDeclineOrderAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(ClientDeclineOrderAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        OrderService orderService = new OrderService(daoFactory);
        Client client = (Client) req.getSession().getAttribute("client");
        Long orderId = Long.valueOf(req.getParameter("orderId"));
        Order order = orderService.findById(orderId);
        /**
         * if no such such order in db just exit from action returning myOrders page
         */
        if (order == null) return new ActionResult("myOrders", true);
        /**
         * if user declined an order when a taxi have already arrived, his bonus will be removed as punishment
         */
        if (order.getStatus() == Order.OrderStatus.CLIENT_EXPECTING) {
            client.setBonus(null);
            ClientService clientService = new ClientService(daoFactory);
            clientService.update(client);
        }
        orderService.deleteById(orderId);
        log.info("client {} declined order", client.getLogin());
        return new ActionResult("myOrders", true);
    }
}
