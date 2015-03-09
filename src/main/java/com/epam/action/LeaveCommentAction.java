package com.epam.action;

import com.epam.dao.DaoException;
import com.epam.dao.DaoFactory;
import com.epam.entity.Client;
import com.epam.entity.Comment;
import com.epam.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class LeaveCommentAction implements Action {
    private Client client;
    private static final Logger log = LoggerFactory.getLogger(LeaveCommentAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        client = (Client) req.getSession().getAttribute("client");
        DaoFactory daoFactory = (DaoFactory) req.getServletContext().getAttribute("daoFactory");
        CommentService commentService = new CommentService(daoFactory);
        Comment comment = createCommentBean(req);
        try {
            commentService.insert(comment);
        } catch (DaoException e) {
            req.setAttribute("error", "client.message.maxCommentSize");
            return new ActionResult("comments");
        }
        log.info("comment was created by client {0}", comment.getClient().getLogin());
        return new ActionResult("comments", true);
    }

    private Comment createCommentBean(HttpServletRequest req) {
        Comment comment = new Comment();
        comment.setClient(client);
        comment.setBody(req.getParameter("body"));
        return comment;
    }
}
