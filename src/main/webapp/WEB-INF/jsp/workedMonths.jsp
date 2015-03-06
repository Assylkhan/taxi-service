<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeFmt.jspf" %>
<fmt:message key="admin.pageTitle.workedMonths" var="title"/>
<t:adminPage title="${title}">
    <div class="list-group">
        <c:forEach items="${months}" var="month">
            <a class="list-group-item"
               href="<c:url value='/employeeOrders'>
                    <c:param name="role" value="${role}"/>
                    <c:param name="employeeId" value="${employeeId}"/>
                    <c:param name="month" value="${month}"/>
                </c:url> ">
                    ${month}
            </a>
        </c:forEach>
    </div>
</t:adminPage>