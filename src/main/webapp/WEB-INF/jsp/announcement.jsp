<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="includeFmt.jspf" %>
<fmt:message var="title" key="announcements.pageTitle.announcement"/>
<c:choose>
    <c:when test="${not empty admin}">
        <t:adminPage title="${title}">
            <%@include file="partialAnnouncement.jspf" %>
        </t:adminPage>
    </c:when>
    <c:when test="${not empty client}">
        <t:clientPage title="${title}">
            <%@include file="partialAnnouncement.jspf" %>
        </t:clientPage>
    </c:when>
    <c:when test="${not empty driver}">
        <t:driverPage title="${title}">
            <%@include file="partialAnnouncement.jspf" %>
        </t:driverPage>
    </c:when>
    <c:when test="${not empty dispatcher}">
        <t:dispatcherPage title="${title}">
            <%@include file="partialAnnouncement.jspf" %>
        </t:dispatcherPage>
    </c:when>
    <c:when test="${not empty admin}">
        <t:adminPage title="${title}">
            <%@include file="partialAnnouncements.jspf"%>
        </t:adminPage>
    </c:when>
    <c:otherwise>
        <t:clientPage title="${title}">
            <%@include file="partialAnnouncement.jspf" %>
        </t:clientPage>
    </c:otherwise>
</c:choose>
