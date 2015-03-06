<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeFmt.jspf" %>
<fmt:message key="admin.reports" var="title"/>
<t:adminPage title="${title}">
    <ul class="list-group">
        <li class="list-group-item">
            <span class="badge">${dispatchersCount}</span>
            <a href="<c:url value='/employees'>
                <c:param name="type" value="dispatchers"/>
            </c:url>">
                <fmt:message key="admin.dispatchers"/>
            </a>
        </li>
        <li class="list-group-item">
            <span class="badge">${driversCount}</span>
            <a href="<c:url value='/employees'>
                <c:param name="type" value="drivers"/>
            </c:url>">
                <fmt:message key="admin.drivers"/>
            </a>
        </li>
    </ul>
</t:adminPage>
