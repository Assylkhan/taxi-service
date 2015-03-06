<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeFmt.jspf" %>
<fmt:message key="admin.employee.orders" var="title"/>
<t:adminPage title="${title}">
    <div id="employeeOrders" class="well">
        <div class="row horizontalCenter">
            <div class="pull-left" style="font-size: 24px">
            ${employee.firstName} ${employee.lastName} <br/>
                <small>${employee.nationalId}</small>
            </div>
            <button class="pull-right btn btn-default" onclick="window.print()">
                <span class="glyphicon glyphicon-print"></span> <fmt:message key="admin.print"/>
            </button>
        </div>
        <br/>
        <table class="table table-striped horizontalCenter">
            <thead>
            <tr>
                <th>#</th>
                <th><fmt:message key="order.pickupLocation"/></th>
                <th><fmt:message key="order.dropOffLocation"/></th>
                <th><fmt:message key="order.carClass"/></th>
                <th><fmt:message key="order.receivedTime"/></th>
            </tr>
            </thead>
            <tbody id="page_0">
            <c:forEach items="${orders}" var="order">
                <tr id="${order.id}">
                    <td>${order.id}</td>
                    <td>${order.pickupLocation}</td>
                    <td>${order.dropOffLocation}</td>
                    <td>${order.carClass}</td>
                    <td>${order.receivedTime}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</t:adminPage>
