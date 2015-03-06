package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Role;
import com.epam.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.YearMonth;
import java.util.List;

public class ShowWorkedMonthsAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory)req.getServletContext().getAttribute("daoFactory");
        OrderService orderService = new OrderService(daoFactory);
        Long employeeId = Long.valueOf(req.getParameter("employeeId"));
        Role role = Role.valueOf(req.getParameter("role"));
        List<YearMonth> months = null;
        if (role == Role.DISPATCHER){
            months = orderService.findMonthsByDispatcherId(employeeId);
        } else {
            months = orderService.findMonthsByDriverId(employeeId);
        }
        req.setAttribute("employeeId", employeeId);
        req.setAttribute("role", role);
        req.setAttribute("months", months);
        return new ActionResult("workedMonths");

    }
}
