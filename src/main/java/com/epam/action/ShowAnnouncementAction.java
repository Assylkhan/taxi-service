package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.entity.Announcement;
import com.epam.service.AnnouncementService;
import com.epam.util.LanguageChanger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAnnouncementAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory)req.getServletContext().getAttribute("daoFactory");
        AnnouncementService announcementService = new AnnouncementService(daoFactory);
        Long announcementId = Long.valueOf(req.getParameter("announcementId"));
        Announcement announcement = announcementService.findById(announcementId);
        LanguageChanger.changeAnnouncementLanguage(req, announcement);
        req.setAttribute("announcement", announcement);
        return new ActionResult("announcement");
    }
}
