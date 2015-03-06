package com.epam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowCurrentOrderAction implements Action {
    ActionResult result = new ActionResult("currentOrder");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        /*ServletContext servletContext = req.getServletContext();
        DaoFactory daoFactory = (DaoFactory) servletContext.getAttribute("daoFactory");
        Driver driver = (Driver) req.getSession().getAttribute("driver");
        Order order = driver.getCurrentOrder();
        if (order == null) {
            OrderService orderService = new OrderService(daoFactory);
            order = orderService.findTakenUpByDriverId(driver.getId());
            driver.setCurrentOrder(order);
            req.getSession().setAttribute("driver", driver);
        }*/
        return result;
    }
}
