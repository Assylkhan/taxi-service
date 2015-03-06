<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeFmt.jspf" %>
<fmt:message key="admin.addAnnouncementTitle" var="title"/>
<fmt:message key="admin.addAnnouncementText" var="placeholder"/>
<t:adminPage title="${title}">
    <div id="addAnnouncement" class="well-sm alert-info row">
        <div class="col-md-9">
            <form action="<c:url value='/addAnnouncement'/>" method="post">
                <t:input name="title" className="col-md-5 form-control" placeholder="${title}"/>
                <textarea name="body" class="form-control" placeholder="${placeholder}" rows="4"></textarea>
                <br/>
                <select name="language">
                    <option value="en">en</option>
                    <option value="ru">ru</option>
                </select><br/><br/>
                <fmt:message key="admin.addAnnouncement.add" var="add"/>
                <t:input type="submit" className="btn btn-default col-md-3" value="${add}"/>
            </form>
        </div>
    </div>
</t:adminPage>
