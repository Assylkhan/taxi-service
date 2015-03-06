package com.epam.validation;

import javax.servlet.http.HttpServletRequest;

public class UserValidator {
    public static boolean validateUser(HttpServletRequest req) {
        boolean isValid = true;
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        if (!InputValidator.nameOrLogin(firstName)) {
            req.setAttribute("firstNameError", "registration.error.incorrectFirstName");
            isValid = false;
        }
        if (!InputValidator.nameOrLogin(lastName)) {
            req.setAttribute("lastNameError", "registration.error.incorrectLastName");
            isValid = false;
        }
        if (!InputValidator.nameOrLogin(login)) {
            req.setAttribute("loginError", "registration.error.incorrectLogin");
            isValid = false;
        }
        if (password.isEmpty() || password == null) {
            req.setAttribute("passwordError", "registration.error.incorrectPassword");
            isValid = false;
        }
        if (!password.equals(confirmPassword)) {
            req.setAttribute("confirmError", "registration.error.confirmPassword");
            isValid = false;
        }
        return isValid;
    }

    public static boolean validateEmployee(HttpServletRequest req) {
        boolean isValid = validateUser(req);
        String nationalId = req.getParameter("nationalId");
        if (!InputValidator.natId(nationalId)) {
            req.setAttribute("natIdError", "registration.error.nationalId");
            isValid = false;
        }
        return isValid;
    }

    public static boolean validateDriver(HttpServletRequest req){
        boolean isValid = validateEmployee(req);
        String govNumber = req.getParameter("govNumber");
        if (govNumber == null || govNumber.isEmpty()){
            req.setAttribute("govNumberError", "driver.error.govNumber");
            isValid = false;
        }
        return isValid;
    }
}
