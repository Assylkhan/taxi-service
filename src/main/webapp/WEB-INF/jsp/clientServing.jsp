<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="includeFmt.jspf" %>
<%--<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<t:clientPage title="sending application">

    <%--<c:if test="${not empty insertOrder}">
        <p><strong style="color: darkred">${insertOrder}</strong></p>
    </c:if>--%>
    <fmt:message key="client.clientProfile" var="clientProfile"/>
    <div role="tabpanel" class="tab-panel">
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="${toOrder}">
                <a href="<c:url value='/clientServing'/> " aria-controls="toOrder">
                    <span class="glyphicon glyphicon-send"></span>
                    <fmt:message key="client.toOrder"/>
                </a>
            </li>
            <li role="presentation" class="${myOrders}">
                <a href="<c:url value='/myOrders'/>" aria-controls="myOrders">
                    <span class="glyphicon glyphicon-list"></span>
                    <fmt:message key="client.myOrders"/>
                </a>
            </li>
            <li role="presentation" class="${notifications}">
                <a href="<c:url value='/clientNotification'/> " aria-controls="notifications" role="tab">
                        <%--${empty client.orders ?  "" : fn:length(client.orders)}--%>
                    <span class="badge"></span>
                    <fmt:message key="client.notifications"/>
                </a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane ${toOrder}" id="toOrder">
                <c:if test="${not empty toOrder}">
                    <%@include file="sendOrder.jspf" %>
                </c:if>
            </div>
            <div role="tabpanel" class="tab-pane ${myOrders}" id="myOrders">
                <c:if test="${not empty myOrders}">
                    <%@include file="myOrders.jspf" %>
                </c:if>
            </div>
            <div role="tabpanel" class="tab-pane ${notifications}" id="notifications">
                <c:if test="${not empty notifications}">
                    <%@include file="clientNotification.jspf" %>
                </c:if>
            </div>
        </div>
    </div>

</t:clientPage>
