<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeFmt.jspf"%>
<t:driverPage>

    <c:choose>
        <c:when test="${not empty driver.currentOrder}">
            <h3 align="center" class="alert alert-info">
                <fmt:message key="driver.currentOrder.flightProcess"/>
            </h3>
            <ol class="progress-tracker">
                <li class="step"><span class="step-name"><fmt:message key="driver.currentOrder.takenUp"/></span></li>
                <li class="step"><span class="step-name"><fmt:message key="order.status.CLIENT_EXPECTING"/></span></li>
                <li class="step"><span class="step-name"><fmt:message key="order.status.IN_PROCESS"/></span></li>
                <li class="step"><span class="step-name"><fmt:message key="order.status.COMPLETED"/></span></li>
            </ol>
            <dl>
                <h2>${driver.currentOrder.status}</h2>
                <dt><fmt:message key="client.firstName"/></dt>
                <dd><c:out value="${driver.currentOrder.client.firstName}" default="unknown"/></dd>

                <dt><fmt:message key="order.pickupLocation"/></dt>
                <dd>${driver.currentOrder.pickupLocation}</dd>

                <dt><fmt:message key="order.dropOffLocation"/></dt>
                <dd>${driver.currentOrder.dropOffLocation}</dd>

                <dt><fmt:message key="order.receivedTime"/></dt>
                <dd>${driver.currentOrder.receivedTime}</dd>
            </dl>
            <c:choose>
                <c:when test="${driver.currentOrder.status == 'ACCEPTED'}">
                    <form id="takeUp" action='<c:url value="/takeUpFlight"/>' method="post">
                        <input class="btn btn-success" type="submit" name="act" value="takeUp"/>
                    </form>
                </c:when>
                <c:when test="${driver.currentOrder.status == 'TAKEN_UP'}">
                    <form id="clientExpecting" action='<c:url value="/notifyClient"/>' method="post">
                        <input class="btn btn-success" type="submit" name="act" value="notifyClient"/>
                    </form>
                </c:when>
                <c:when test="${driver.currentOrder.status == 'CLIENT_EXPECTING'}">
                    <form id="start" action='<c:url value="/startFlight"/>' method="post">
                        <input class="btn btn-success" type="submit" name="act" value="start"/>
                    </form>
                </c:when>
                <c:when test="${driver.currentOrder.status == 'IN_PROCESS'}">
                    <form id="end" action='<c:url value="/endFlight"/>' method="post">
                        <input class="btn btn-success" type="submit" name="act" value="end"/>
                    </form>
                </c:when>
            </c:choose>
        </c:when>
        <c:otherwise>
            <h2 class="alert alert-info" align=center>
                <fmt:message key="driver.message.currentlyNoOrders"/>
            </h2>
        </c:otherwise>
    </c:choose>

</t:driverPage>
