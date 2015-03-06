<%@tag description="Client Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/WEB-INF/jsp/includeFmt.jspf"%>
<%@attribute name="title" %>
<t:genericPage title="${title}" role="driver">
    <jsp:attribute name="header">
        <t:languageToggler/>
        <ul class="list-unstyled pull-right">
            <c:choose>
                <c:when test="${not empty sessionScope.driver}">
                    <li><strong><a href="<c:url value='/currentOrder'/>">
                        <fmt:message key="driver.navbar.currentOrder"/>
                    </a></strong></li>
                    <li><strong><a href="<c:url value='/receivedOrders'/> ">
                        <fmt:message key="driver.navbar.receivedOrders"/>
                    </a></strong>
                    </li>
                    <li><strong><a href="<c:url value='/driverProfile'/>">
                        <fmt:message key="user.profile"/>
                    </a></strong></li>
                    <li><strong><a href="<c:url value='/logout'/>">
                        <fmt:message key="login.logout"/>
                    </a></strong></li>
                </c:when>
                <c:otherwise>
                    <li><strong><a href="<c:url value='/login'/>">
                        <fmt:message key="login.button.login"/>
                    </a></strong></li>
                    <li><strong><a href="<c:url value='/registerDriver'/>">
                        <fmt:message key="registration.register"/>
                    </a></strong></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</t:genericPage>
