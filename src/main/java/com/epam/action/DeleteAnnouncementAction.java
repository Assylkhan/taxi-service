package com.epam.action;

import com.epam.dao.DaoFactory;
import com.epam.service.AnnouncementService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class DeleteAnnouncementAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(DeleteAnnouncementAction.class);

    /**
     * admin removes announcement
     * @param req
     * @param resp
     * @return
     */
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        AnnouncementService announcementService = new AnnouncementService(daoFactory);
        Long announcementId = Long.valueOf(req.getParameter("announcementId"));
        announcementService.deleteById(announcementId);
        log.info("announcement by id {} was deleted by admin",announcementId );
        return new ActionResult("announcements", true);
    }
}
