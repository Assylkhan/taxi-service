package com.epam.action;

import com.epam.dao.DaoException;
import com.epam.dao.DaoFactory;
import com.epam.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class RemoveOrderAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(RemoveOrderAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        OrderService orderService = new OrderService(daoFactory);
        Long orderId = Long.valueOf(req.getParameter("orderId"));
        try {
            orderService.deleteById(orderId);
        } catch (DaoException e) {
            resp.setStatus(500);
            throw e;
        }
        log.info("removed order {}", orderId);
        req.setAttribute("data", "removed");
        return new ActionResult("json");
    }
}
