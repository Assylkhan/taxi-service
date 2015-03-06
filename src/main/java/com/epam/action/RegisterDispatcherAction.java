package com.epam.action;

import com.epam.dao.DaoException;
import com.epam.dao.DaoFactory;
import com.epam.dao.DatabaseType;
import com.epam.entity.Dispatcher;
import com.epam.service.DispatcherService;
import com.epam.util.UserFactory;
import com.epam.validation.UserValidator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterDispatcherAction implements Action {
    ActionResult registerAgain = new ActionResult("registerDispatcher");
    private static final Logger log = LoggerFactory.getLogger(RegisterAdminAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        boolean valid = UserValidator.validateEmployee(req);
        if (!valid) return registerAgain;
        DaoFactory factory = DaoFactory.getDaoFactory(DatabaseType.H2);
        Dispatcher dispatcher = getDispatcherBean(req);
        DispatcherService service = new DispatcherService(factory);
        try {
            service.insert(dispatcher);
        } catch (DaoException e){
            log.error("exception while registering ", e);
            req.setAttribute("natIdError", "employee.message.nationalIdTaken");
            return registerAgain;
        }
        log.info("registered new dispatcher by login {0}", dispatcher.getLogin());
        req.getSession().setAttribute("dispatcher", dispatcher);
        return new ActionResult("orderServing", true);
    }

    private Dispatcher getDispatcherBean(HttpServletRequest req) {
        Dispatcher dispatcher = (Dispatcher) UserFactory.createUserBean(req, Dispatcher.class.getName());
        dispatcher.setNationalId(req.getParameter("nationalId"));
        dispatcher.setPhone(req.getParameter("phone"));
        return dispatcher;
    }
}
