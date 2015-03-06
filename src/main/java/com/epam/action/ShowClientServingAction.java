package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Client;
import com.epam.entity.Order;
import com.epam.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowClientServingAction implements Action {
    ActionResult result = new ActionResult("clientServing");
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("toOrder", "active");
        return result;
    }
}
