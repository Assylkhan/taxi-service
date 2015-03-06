package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.*;
import com.epam.service.UserService;
import com.epam.util.HashGenerator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class LoginAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);
    private Role role;
    private String login;
    private String password;
    private String path;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        login = req.getParameter("login");
        password = req.getParameter("password");
        role = Role.valueOf(req.getParameter("role").toUpperCase());
        path = req.getParameter("path");
        if (isNotEmptyAttributes() == false) {
            ActionResult error = getErrorForUser(req);
            return error;
        }
        String generatedPassword = HashGenerator.passwordToHash(password);
        DaoFactory daoFactory = (DaoFactory) req.getSession().getServletContext().getAttribute("daoFactory");
        UserService userService = new UserService(daoFactory);
        User user = userService.findByCredentials(login, generatedPassword, role);
        if (user == null) {
            ActionResult error = getErrorForUser(req);
            return error;
        }
        log.info("logged in user {0}, his role is: ", user.getLogin(), user.getRole());
        ActionResult result = getActionResultByRole(req, user);
        return result;
    }

    private ActionResult getErrorForUser(HttpServletRequest req) {
        req.setAttribute("loginError", "login.loginOrPasswordIncorrect");
        return new ActionResult(path);
    }

    private boolean isNotEmptyAttributes() {
        return (role != null && !role.toString().isEmpty() &&
                login != null && !login.isEmpty() &&
                password != null && !password.isEmpty() &&
                path != null && !path.isEmpty());
    }

    private ActionResult getActionResultByRole(HttpServletRequest req, User user) {
        switch (role) {
            case ADMIN:
                req.getSession().setAttribute("admin", (Admin) user);
                return new ActionResult("reports", true);
            case DISPATCHER:
                req.getSession().setAttribute("dispatcher", (Dispatcher) user);
                return new ActionResult("orderServing", true);
            case DRIVER:
                req.getSession().setAttribute("driver", (Driver) user);
                return new ActionResult("receivedOrders", true);
            default:
                req.getSession().setAttribute("client", (Client) user);
                return new ActionResult("clientServing", true);
        }
    }
}
