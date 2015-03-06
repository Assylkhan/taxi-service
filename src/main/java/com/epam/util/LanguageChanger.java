package com.epam.util;

import com.epam.entity.Announcement;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

public class LanguageChanger {
    public static void changeAnnouncementsLanguage(HttpServletRequest req, List<Announcement> announcements) {
        if (announcements == null) return;
        for (Announcement announcement : announcements) {
            changeAnnouncementLanguage(req, announcement);
        }
    }

    /**
     * Change announcement language versions according site's language.
     * if no version in one language set to version that exists
     *
     * @param req
     * @param announcement
     */
    public static void changeAnnouncementLanguage(HttpServletRequest req, Announcement announcement) {
        if (announcement == null) return;
        Locale locale = (Locale) req.getSession().getAttribute("locale");
        String reqLanguage = req.getParameter("language");
        locale = locale != null ? locale : req.getLocale();
        String language = reqLanguage != null ? reqLanguage : locale.getLanguage();
        if (language.equals("en") && announcement.getBodyEn() != null) {
            announcement.setTitleRu(null);
            announcement.setBodyRu(null);
        } else if (language.equals("ru") && announcement.getBodyRu() != null) {
            announcement.setTitleEn(null);
            announcement.setBodyEn(null);
        }
    }
}
