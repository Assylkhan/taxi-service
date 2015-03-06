<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="includeFmt.jspf" %>
<fmt:message key="driver.pageTitle.receivedOrders" var="title"/>
<fmt:message key="driver.order.accept" var="accept"/>
<fmt:message key="driver.order.decline" var="decline"/>
<fmt:message key="driver.order.serve" var="serve"/>
<t:driverPage title="${title}">
    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <c:set var="orders" value="${driver.orders}"/>
        <c:if test="${empty orders}">
            <h2 align=center class="alert alert-info horizontalCenter">
                <fmt:message key="driver.message.currentlyNoOrders"/>
            </h2>
        </c:if>
        <c:forEach items="${orders}" var="order" varStatus="theCount">
            <div class="panel panel-info">
                <div class="panel-heading" role="tab">
                    <a data-toggle="collapse" data-parent="#accordion"
                       href="#pan_${theCount.count}" aria-expanded="true" aria-controls="collapseOne">
                        <strong>${order.pickupLocation} - ${order.dropOffLocation}</strong>
                    </a>
                </div>
                <div id="pan_${theCount.count}"
                     class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        <dl class="dl">
                            <p>
                                <fmt:formatDate value="${order.receivedTime}" pattern="dd.MM.yyyy hh:mm"/>
                            </p>
                            <dt><fmt:message key="client.firstName"/></dt>
                            <dd>${order.client.firstName}</dd>

                            <dt><fmt:message key="order.pickupLocation"/></dt>
                            <dd>${order.pickupLocation}</dd>

                            <dt><fmt:message key="order.dropOffLocation"/></dt>
                            <dd>${order.dropOffLocation}</dd>

                            <dt><fmt:message key="order.status"/></dt>
                            <dd><fmt:message key="order.status.${order.status}"/></dd>
                            <br/>

                            <div class="flexed-group">
                                <c:choose>
                                    <c:when test="${order.status == 'NOT_SERVED'}">
                                        <form action='<c:url value="/driverConfirmation"/>' method="post">
                                            <input type="hidden" name="orderId" value="${order.id}">
                                            <input class="btn btn-success" type="submit" name="act" value="accept"/>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form action='<c:url value="/driverOrderServe"/>' method="post">
                                            <input type="hidden" name="orderId" value="${order.id}">
                                            <input class="btn btn-danger" type="submit" name="act" value="serve"/>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                                <form action='<c:url value="/driverConfirmation"/>' method="post">
                                    <input type="hidden" name="orderId" value="${order.id}">
                                    <input class="btn btn-danger" type="submit" name="act" value="decline"/>
                                </form>
                            </div>
                        </dl>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</t:driverPage>
