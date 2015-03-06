package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Announcement;
import com.epam.service.AnnouncementService;
import com.epam.util.LanguageChanger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAnnouncementsAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory)req.getServletContext().getAttribute("daoFactory");
        AnnouncementService announcementService = new AnnouncementService(daoFactory);
        List<Announcement> announcements = announcementService.findAll();
        LanguageChanger.changeAnnouncementsLanguage(req, announcements);
        req.setAttribute("announcements", announcements);
        return new ActionResult("announcements");
    }
}
