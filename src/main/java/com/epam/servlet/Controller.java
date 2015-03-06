package com.epam.servlet;

import com.epam.action.Action;
import com.epam.action.ActionFactory;
import com.epam.action.ActionResult;
import com.epam.dao.DaoFactory;
import com.epam.entity.Announcement;
import com.epam.entity.Comment;
import com.epam.service.AnnouncementService;
import com.epam.service.CommentService;
import com.epam.util.LanguageChanger;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Controller extends HttpServlet {

    private ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        actionFactory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String substring = req.getPathInfo();
        String actionName = req.getMethod() + substring;
        Action action = actionFactory.getAction(actionName);

        if (action == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Not Found");
            return;
        }

        setRecentNews(req, resp);

        ActionResult result = action.execute(req, resp);

        doForwardOrRedirect(result, req, resp);
    }

    private void doForwardOrRedirect(ActionResult result, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (result.isRedirect()) {
            String location = req.getContextPath() + "/" + result.getView();
            resp.sendRedirect(location);
        } else if (result.getView().startsWith("json")) {
            outJson(req, resp);
        } else {
            String path = String.format("/WEB-INF/jsp/" + result.getView() + ".jsp");
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }

    /**
     * responses by json data: response consist from one object or from objects array
     * @param req
     * @param resp
     * @throws IOException
     */
    private void outJson(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if (req.getAttribute("data").toString().startsWith("["))
            out.write(req.getAttribute("data").toString());
        else
            out.write(new Gson().toJson(req.getAttribute("data")));
        out.flush();
    }

    /**
     * set in all page's side-bars recent news and comments
     * @param req
     * @param resp
     */
    private void setRecentNews(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getSession().getAttribute("recentComments") == null) {
            DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
            CommentService commentService = new CommentService(daoFactory);
            List<Comment> comments = commentService.findRecentWithLimit(3);
            req.getSession().setAttribute("recentComments", comments);
        }
        if (req.getSession().getAttribute("recentAnnouncements") == null) {
            DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
            AnnouncementService announcementService = new AnnouncementService(daoFactory);
            List<Announcement> announcements = announcementService.findRecentWithLimit(3);
            LanguageChanger.changeAnnouncementsLanguage(req, announcements);
            req.getSession().setAttribute("recentAnnouncements", announcements);
        }
    }
}
