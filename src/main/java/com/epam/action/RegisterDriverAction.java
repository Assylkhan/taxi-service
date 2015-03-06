package com.epam.action;

import com.epam.dao.DaoException;
import com.epam.dao.DaoFactory;
import com.epam.dao.DatabaseType;
import com.epam.entity.Driver;
import com.epam.service.DriverService;
import com.epam.util.UserFactory;
import com.epam.validation.UserValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class RegisterDriverAction implements Action {
    ActionResult registerAgain = new ActionResult("registerDriver");
    private static final Logger log = LoggerFactory.getLogger(RegisterDispatcherAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        boolean valid = UserValidator.validateDriver(req);
        if (!valid) return registerAgain;
        DaoFactory factory = DaoFactory.getDaoFactory(DatabaseType.H2);
        Driver driver = createDriverBean(req);
        DriverService service = new DriverService(factory);
        try {
            service.insert(driver);
        } catch (DaoException e){
            log.error("exception while registering ", e);
            req.setAttribute("natIdError", "employee.message.nationalIdTaken");
            return registerAgain;
        }
        log.info("registered new driver by login {0}", driver.getLogin());
        req.getSession().setAttribute("driver", driver);
        return new ActionResult("receivedOrders", true);
    }

    private Driver createDriverBean(HttpServletRequest req) {
        Driver driver = (Driver) UserFactory.createUserBean(req, Driver.class.getName());
        driver.setNationalId(req.getParameter("nationalId"));
        driver.setPhone(req.getParameter("phone"));
        driver.setGovNumber(req.getParameter("govNumber"));
        driver.setCarClass(req.getParameter("carClass"));
        driver.setCarModel(req.getParameter("carModel"));
        return driver;
    }
}
