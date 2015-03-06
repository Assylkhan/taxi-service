package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Dispatcher;
import com.epam.entity.Driver;
import com.epam.service.DispatcherService;
import com.epam.service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowEmployeesAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        if (req.getParameter("type").equals("drivers")) {
            DriverService driverService = new DriverService(daoFactory);
            List<Driver> drivers = driverService.findAll();
            req.setAttribute("drivers", drivers);
        } else {
            DispatcherService dispatcherService = new DispatcherService(daoFactory);
            List<Dispatcher> dispatchers = dispatcherService.findAll();
            req.setAttribute("dispatchers", dispatchers);
        }
        return new ActionResult("employees");
    }
}
