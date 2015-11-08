package com.epam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

import com.epam.dao.DaoFactory;
import com.epam.entity.Announcement;
import com.epam.service.AnnouncementService;
import com.epam.util.LanguageChanger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ChangeLocaleAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(ChangeDriverStateAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String header = req.getHeader("referer");
        header = header.substring(header.lastIndexOf("/") + 1);
        ActionResult currentPage = new ActionResult(header, true);
        String language = req.getParameter("language");
        HttpSession session = req.getSession();
//        if (language.length() > 2)
//            session.setAttribute("language", Locale.forLanguageTag(language.replace("_", "-")));
//        else
        changeAnnouncementLanguage(req);
        session.setAttribute("locale", new Locale(language));
        log.info("changed language to {}", language);
        return currentPage;
    }

    private static void changeAnnouncementLanguage(HttpServletRequest req){
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        AnnouncementService announcementService = new AnnouncementService(daoFactory);
        List<Announcement> announcements = announcementService.findRecentWithLimit(3);
        LanguageChanger.changeAnnouncementsLanguage(req, announcements);
        req.getSession().setAttribute("recentAnnouncements", announcements);
    }
}
