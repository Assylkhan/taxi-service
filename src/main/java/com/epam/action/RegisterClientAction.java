package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.Client;
import com.epam.service.ClientService;
import com.epam.util.UserFactory;
import com.epam.validation.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class RegisterClientAction implements Action {
    ActionResult registerAgain = new ActionResult("register");
    private static final Logger log = LoggerFactory.getLogger(RegisterAdminAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        boolean valid = UserValidator.validateUser(req);
        if (!valid) return registerAgain;
        DaoFactory factory = DaoFactory.getDaoFactory(DatabaseType.H2);
        Client client = (Client) UserFactory.createUserBean(req, Client.class.getName());
        ClientService clientService = new ClientService(factory);
        clientService.insert(client);
        log.info("registered new client by login {0}", client.getLogin());
        req.getSession().setAttribute("client", client);
        return new ActionResult("clientServing", true);
    }
}
