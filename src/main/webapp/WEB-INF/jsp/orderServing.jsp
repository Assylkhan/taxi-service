<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="includeFmt.jspf" %>
<fmt:message key="dispatcher.pageTitle.orderServing" var="orderServing"/>
<t:dispatcherPage title="${orderServing}">
    <div align=center class="orderServing alert-warning alert">
        <c:if test="${not empty error}">
            <p class="alert-danger">
                <strong><fmt:message key="${error}"/></strong>
            </p>
        </c:if>
        <p><strong class="alert-success">
            <c:if test="${not empty chosenDriver}">
                <fmt:message key="${chosenDriver}"/>
            </c:if>
        </strong></p>

        <form id="serveOrderForm" action='<c:url value="/chooseDriver"/>' method="post">
            <div class="row horizontalCenter">
                <h3 class="pull-left">
                    <fmt:message key="dispatcher.message.chooseDriver"/>
                </h3>
                <button type="button" id="updateRecords" class="btn btn-default pull-right" style="margin-top: 15px">
                    <fmt:message key="dispatcher.updateRecords"/>
                </button>
            </div>
            <select id="driversList" name="driverId" size="5" class="form-control">
                <c:forEach items="${drivers}" var="driver">
                    <option value="${driver.id}">
                        first name: ${driver.firstName} |
                        last name: ${driver.lastName} |
                        phone: ${driver.phone} |
                        available: ${driver.available} |
                        current location: <c:out value="${driver.currentLocation}" default="unknown"/>
                    </option>
                </c:forEach>
                <option value="${driver.id}">
                    имя: Игорь |
                    фамилия: Тимофеев |
                    телефон: 8758-414-42-85|
                    доступен: да |
                    текущее место: гапеева-3, дом-4
                </option>
            </select>

            <h3><fmt:message key="dispatcher.message.chooseApplication"/></h3>

            <table id="orders" class="table-bordered horizontalCenter centered-children">
                <thead>

                <tr>
                    <th>#</th>
                    <th><fmt:message key="dispatcher.orderServing.clientName"/></th>
                    <th><fmt:message key="order.pickupLocation"/></th>
                    <th><fmt:message key="order.dropOffLocation"/></th>
                    <th><fmt:message key="client.carType"/></th>
                    <th>бонус, тг</th>
                    <th><fmt:message key="order.receivedTime"/></th>
                    <th><fmt:message key="order.status"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="page_0">
                <c:forEach items="${pagOrders.items}" var="order">
                    <tr id="${order.id}">
                        <td>${order.id}</td>
                        <td><c:out value="${order.client.firstName}" default="unknown client"/></td>
                        <td>${order.pickupLocation}</td>
                        <td>${order.dropOffLocation}</td>
                        <td>${order.carClass}</td>
                        <td>0</td>
                        <td>${order.receivedTime}</td>
                        <td><fmt:message key="order.status.${order.status}"/></td>
                        <td>
                            <button id="removeOrder" value="${order.id}" class=" btn-danger" type="button">
                                <fmt:message key="dispatcher.order.remove"/>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <c:if test="${pagOrders.last == true}">
                <c:set value="disabled" var="property"/>
            </c:if>
            <div class="row horizontalCenter" style="margin: 0 50px;">
                <button id="prev" type="button" class="btn btn-success pull-left disabled">
                    <span class="glyphicon glyphicon-backward"></span>
                </button>
                <button id="next" type="button" class="${property} btn btn-success pull-left"
                        style="margin-left: 617px">
                    <span class="glyphicon glyphicon-forward"></span>
                </button>
            </div>

            <input name="orderId" id="orderId" type="hidden"/>
            <br/><br/>

            <fmt:message key="dispatcher.message.setCost" var="setCost"/>
            <t:input className="col-md-3 horizontalCenter input-group" name="cost"
                     label="${setCost}"/>

            <div class="row horizontalCenter" style="margin-top: 15px">
                <button id="serveOrder" type="submit" class="pull-right btn btn-serve btn-lg">
                    <fmt:message key="dispatcher.message.submit"/>
                </button>
            </div>
        </form>
    </div>

</t:dispatcherPage>
