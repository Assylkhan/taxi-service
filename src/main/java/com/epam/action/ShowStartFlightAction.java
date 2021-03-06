package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Driver;
import com.epam.entity.Order;
import com.epam.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowStartFlightAction implements Action {
    ActionResult currentOrder = new ActionResult("currentOrder", true);
    ActionResult startFlight = new ActionResult("startFlight");
    ActionResult processingFlight = new ActionResult("processingFlight", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        Driver driver = (Driver) req.getSession().getAttribute("driver");
        Order order = driver.getCurrentOrder();
        if (order == null){
            OrderService orderService = new OrderService(daoFactory);
            order = orderService.findTakenUpByDriverId(driver.getId());
            driver.setCurrentOrder(order);
            req.getSession().setAttribute("order", order);
            if (order == null) return currentOrder;
        }
        switch (order.getStatus()){
            case NOT_SERVED:
                return currentOrder;
            case ACCEPTED:
                return startFlight;
            case IN_PROCESS:
                return processingFlight;
        }
        return startFlight;
    }
}
