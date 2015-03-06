package com.epam.util;

import com.epam.entity.User;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UserFactory {
    private static final Logger log = LoggerFactory.getLogger(UserFactory.class);

    public static User createUserBean(HttpServletRequest req, String className) {
        User user = null;
        try {
            user = (User) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            log.error("exception: ", e);
        } catch (IllegalAccessException e) {
            log.error("exception: ", e);
        } catch (ClassNotFoundException e) {
            log.error("exception: ", e);
        }
        user.setLogin(req.getParameter("login"));
        String generatedPassword = HashGenerator.passwordToHash(req.getParameter("password"));
        user.setPassword(generatedPassword);
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        return user;
    }
}
