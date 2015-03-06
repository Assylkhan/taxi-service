package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.dao.Page;
import com.epam.entity.Driver;
import com.epam.entity.Order;
import com.epam.service.DriverService;
import com.epam.service.OrderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowOrderServingAction implements Action {
    ActionResult result = new ActionResult("orderServing");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        DaoFactory daoFactory = (DaoFactory) servletContext.getAttribute("daoFactory");
        DriverService driverService = new DriverService(daoFactory);
        OrderService orderService = new OrderService(daoFactory);
        Page<Order> page = new Page<>();
        page.setPageNumber(0);
        page.setCountPerPage(5);
        Page<Order> paginatedOrders = orderService.paginateNotServed(page);
        List<Driver> drivers = driverService.findAvailableDrivers();
        req.setAttribute("drivers", drivers);
        req.setAttribute("pagOrders", paginatedOrders);
        if (req.getParameter("data") != null && req.getParameter("data").equals("json")) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String stringOrders = gson.toJson(paginatedOrders);
            String stringDrivers = gson.toJson(drivers);
            String data = "["+stringOrders+","+stringDrivers+"]";
            req.setAttribute("data", data);
            return new ActionResult("json");
        }
        return result;
    }
}
