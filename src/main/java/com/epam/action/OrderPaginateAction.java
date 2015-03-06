package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.dao.Page;
import com.epam.entity.Order;
import com.epam.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderPaginateAction implements Action {
    ActionResult result = new ActionResult("json");
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory)req.getServletContext().getAttribute("daoFactory");
        OrderService orderService = new OrderService(daoFactory);
        int pageNumber = Integer.valueOf(req.getParameter("pageNumber"));
        Page.Builder<Order> pageBuilder = new Page.Builder<>();
        Page page = pageBuilder.pageNumber(pageNumber).perPage(5).build();
        Page<Order> evaluatedPage = orderService.paginateNotServed(page);
        req.setAttribute("data", evaluatedPage);
        return result;
    }
}
