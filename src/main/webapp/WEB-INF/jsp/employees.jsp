<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeFmt.jspf" %>
<fmt:message key="admin.drivers" var="title"/>
<t:adminPage title="${title}">
    <div class="list-group">
        <c:set var="employees" value="${drivers == null ? dispatchers : drivers}"/>
        <c:forEach items="${employees}" var="employee">
            <a class="list-group-item"
               href="<c:url value='/workedMonths'>
                    <c:param name="role" value="${employee.role}"/>
                    <c:param name="employeeId" value="${employee.id}"/>
                </c:url> ">
                    ${employee.firstName} ${employee.lastName}
            </a>
        </c:forEach>
    </div>
</t:adminPage>

