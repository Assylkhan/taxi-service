<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="includeFmt.jspf" %>
<fmt:message var="commentTitle" key="comment.pageTitle"/>
<c:choose>
    <c:when test="${not empty admin}">
        <t:adminPage title="${commentTitle}">
            <%@include file="partialComments.jspf" %>
        </t:adminPage>
    </c:when>
    <c:when test="${not empty client}">
        <t:clientPage title="${commentTitle}">
            <%@include file="partialComments.jspf" %>
        </t:clientPage>
    </c:when>
    <c:when test="${not empty driver}">
        <t:driverPage title="${commentTitle}">
            <%@include file="partialComments.jspf" %>
        </t:driverPage>
    </c:when>
    <c:when test="${not empty dispatcher}">
        <t:dispatcherPage title="${commentTitle}">
            <%@include file="partialComments.jspf" %>
        </t:dispatcherPage>
    </c:when>
    <c:otherwise>
        <t:clientPage title="${commentTitle}">
            <%@include file="partialComments.jspf" %>
        </t:clientPage>
    </c:otherwise>
</c:choose>
