<%@tag description="Dispatcher Page Template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/WEB-INF/jsp/includeFmt.jspf" %>
<%@attribute name="title" %>
<t:genericPage title="${title}" role="dispatcher">
    <jsp:attribute name="header">
        <t:languageToggler/>
        <ul class="list-unstyled pull-right">
            <c:choose>
                <c:when test="${not empty sessionScope.dispatcher }">
                    <li><a href="<c:url value='/orderServing'/>">
                        <fmt:message key="dispatcher.navbar.orderServing"/>
                    </a></li>
                    <li><a href="<c:url value='/orders'/>">
                        <fmt:message key="dispatcher.navbar.orders"/>
                    </a></li>
                    <li class="dropdown" id="menu1">
                        <a class="dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown"
                           href="javascript:void(0)">
                            <c:out value="${dispatcher.firstName}" default="no dispatcher"/>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li role="presentation">
                                <a role="menuitem" tabindex="-1" href="<c:url value='/dispatcherProfile'/>">
                                    <fmt:message key="user.profile"/>
                                </a>
                            </li>
                            <li role="presentation">
                                <a role="menuitem" tabindex="-1" href="<c:url value='/logout'/>">
                                    <fmt:message key="login.logout"/>
                                </a>
                            </li>
                        </ul>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value='/dispatcherLogin'/>">
                        <fmt:message key="login.button.login"/>
                    </a></li>
                    <li><a href="<c:url value='/registerDispatcher'/>">
                        <fmt:message key="registration.register"/>
                    </a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</t:genericPage>
