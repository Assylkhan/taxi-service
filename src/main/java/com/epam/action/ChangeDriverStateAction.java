package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Driver;
import com.epam.service.DriverService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeDriverStateAction implements Action {
    ActionResult result = new ActionResult("driverProfile", true);
    private static final Logger log = LoggerFactory.getLogger(ChangeDriverStateAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        DriverService driverService = new DriverService(daoFactory);
        String checkBox = req.getParameter("available");
        boolean available = false;
        if (checkBox != null) available = true;
        String location = req.getParameter("location");
        Driver driver = (Driver) req.getSession().getAttribute("driver");
        driver.setAvailable(available);
        driver.setCurrentLocation(location);
        /**
         * update driver state in db
         */
        driverService.updateState(driver);
        log.info("driver {} state changed to {1}, current location: {2}",
                driver.getLogin(), driver.isAvailable(), driver.getCurrentLocation());
        /**
         * set updated driver to session
         */
        req.getSession().setAttribute("driver", driver);
        req.setAttribute("flash.changeResult", "driver.message.statusChanged");
        return result;
    }
}
