package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Dispatcher;
import com.epam.entity.Driver;
import com.epam.entity.Order;
import com.epam.entity.Role;
import com.epam.service.DispatcherService;
import com.epam.service.DriverService;
import com.epam.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.YearMonth;
import java.util.List;

public class ShowEmployeeOrdersAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        Role role = Role.valueOf(req.getParameter("role"));
        Long employeeId = Long.valueOf(req.getParameter("employeeId"));
        String stringMonth = req.getParameter("month");
        YearMonth month = YearMonth.parse(stringMonth);
        OrderService orderService = new OrderService(daoFactory);
        List<Order> orders = null;
        if (role == Role.DRIVER) {
            orders = orderService.findByDriverId(employeeId);
            DriverService driverService = new DriverService(daoFactory);
            Driver driver = driverService.findById(employeeId);
            req.setAttribute("employee", driver);
        } else {
            orders = orderService.findByDispatcherId(employeeId);
            DispatcherService dispatcherService = new DispatcherService(daoFactory);
            Dispatcher dispatcher = dispatcherService.findById(employeeId);
            req.setAttribute("employee", dispatcher);
        }
        req.setAttribute("orders", orders);
        return new ActionResult("employeeOrders");
    }
}
