package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Order;
import com.epam.entity.Client;
import com.epam.entity.CarClass;
import com.epam.service.ClientService;
import com.epam.service.OrderService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class SendOrderAction implements Action {
    private ActionResult result = new ActionResult("clientServing", true);
    private static final Logger log = LoggerFactory.getLogger(SendOrderAction.class);
    private String dropOffLocation;
    private String pickupLocation;

    /**
     * sending order by client, that is, new order will be saved in db
     *
     * @param req
     * @param resp
     * @return
     */
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        if (isEmptyParameters(req)) {
            req.setAttribute("error", "dispatcher.message.cannotBeBlank");
            req.setAttribute("toOrder", "active");
            return new ActionResult("clientServing");
        }
        ServletContext servletContext = req.getSession().getServletContext();
        DaoFactory daoFactory = (DaoFactory) servletContext.getAttribute("daoFactory");
        OrderService orderService = new OrderService(daoFactory);
        Order order = createOrderBean(req);
        Order insertedOrder = orderService.insert(order);
        req.setAttribute("flash.successfullySent", "client.message.orderSuccessfullySent");
        req.setAttribute("order", insertedOrder);
        return result;
    }

    private boolean isEmptyParameters(HttpServletRequest req) {
        pickupLocation = req.getParameter("pickupLocation");
        dropOffLocation = req.getParameter("dropOffLocation");
        return (dropOffLocation != null && dropOffLocation.isEmpty() &&
                pickupLocation != null && pickupLocation.isEmpty());
    }

    public Order createOrderBean(HttpServletRequest req) {
        Order order = new Order();
        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute("client");
        order.setClient(client);
        order.setCarClass(CarClass.valueOf(req.getParameter("carType")));
        order.setPickupLocation(pickupLocation);
        order.setDropOffLocation(dropOffLocation);
        order.setStatus(Order.OrderStatus.NOT_SERVED);
        return order;
    }
}
