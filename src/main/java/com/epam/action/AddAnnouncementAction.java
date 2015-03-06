package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Announcement;
import com.epam.service.AnnouncementService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAnnouncementAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(AddAnnouncementAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        AnnouncementService announcementService = new AnnouncementService(daoFactory);
        Announcement announcement = createAnnouncementBean(req);
        announcementService.insert(announcement);
        log.info("new announcement created: {0}", announcement.getTitleEn() + announcement.getTitleRu());
        return new ActionResult("announcements", true);
    }

    /**
     * create Announcement bean from request parameters
     * @param req
     * @return instance of Announcement
     */
    private Announcement createAnnouncementBean(HttpServletRequest req) {
        Announcement announcement = new Announcement();
        String title = req.getParameter("title");
        String body = req.getParameter("body");
        if (req.getParameter("language").equals("en")) {
            announcement.setTitleEn(title);
            announcement.setBodyEn(body);
        } else if (req.getParameter("language").equals("ru")) {
            announcement.setTitleRu(title);
            announcement.setBodyRu(body);
        }
        return announcement;
    }
}
