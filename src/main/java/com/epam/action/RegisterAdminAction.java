package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Admin;
import com.epam.service.AdminService;
import com.epam.util.UserFactory;
import com.epam.validation.UserValidator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAdminAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(RegisterAdminAction.class);
    ActionResult registerAgain = new ActionResult("registerAdmin");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        boolean valid = UserValidator.validateUser(req);
        if (!valid) return registerAgain;
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        Admin admin = (Admin)UserFactory.createUserBean(req, Admin.class.getName());
        AdminService adminService = new AdminService(daoFactory);
        Admin insertedAdmin = adminService.insert(admin);
        log.info("registered new admin by login {0}", insertedAdmin.getLogin());
        req.getSession().setAttribute("admin", admin);
        return new ActionResult("reports", true);
    }
}
