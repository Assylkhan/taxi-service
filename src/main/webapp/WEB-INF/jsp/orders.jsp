<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="includeFmt.jspf" %>
<t:dispatcherPage title="flights">
    <c:choose>
        <c:when test="${not empty orders}">
            <table class="table table-bordered">
                <th>#</th>
                <th><fmt:message key="dispatcher.orders.driverName"/></th>
                <th><fmt:message key="dispatcher.orderServing.clientName"/></th>
                <th><fmt:message key="order.pickupLocation"/></th>
                <th><fmt:message key="order.dropOffLocation"/></th>
                <th><fmt:message key="order.receivedTime"/></th>
                <th><fmt:message key="order.carClass"/></th>
                <th><fmt:message key="order.cost"/></th>
                <th><fmt:message key="order.status"/></th>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td><c:out value="${order.driver.firstName}"/></td>
                        <td><c:out value="${order.client.firstName}" default="unknown client"/></td>
                        <td>${order.pickupLocation}</td>
                        <td>${order.dropOffLocation}</td>
                        <td>${order.receivedTime}</td>
                        <td>
                            <fmt:message key="driver.carClass.${order.carClass}"/>
                        </td>
                        <fmt:message key="order.cost.notSet" var="notSet"/>
                        <td><c:out value="${order.cost}" default="${notSet}"/></td>
                        <td><fmt:message key="order.status.${order.status}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <h3><strong><fmt:message key="dispatcher.message.noOrders"/></strong></h3>
        </c:otherwise>
    </c:choose>
</t:dispatcherPage>
