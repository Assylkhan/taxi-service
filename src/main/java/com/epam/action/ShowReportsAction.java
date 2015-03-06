package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.service.DispatcherService;
import com.epam.service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowReportsAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory)req.getServletContext().getAttribute("daoFactory");
        DispatcherService dispatcherService = new DispatcherService(daoFactory);
        int dispatchersCount = dispatcherService.count();
        DriverService driverService = new DriverService(daoFactory);
        int driversCount = driverService.count();
        req.setAttribute("dispatchersCount", dispatchersCount);
        req.setAttribute("driversCount", driversCount);
        return new ActionResult("reports");
    }
}
