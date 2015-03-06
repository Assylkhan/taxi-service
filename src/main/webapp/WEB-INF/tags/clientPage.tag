<%@tag description="Client Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/WEB-INF/jsp/includeFmt.jspf" %>
<%@attribute name="title" %>
<t:genericPage title="${title}" role="client">
    <jsp:attribute name="header">
        <t:languageToggler/>
        <ul class="list-unstyled pull-right">
            <c:choose>
                <c:when test="${not empty sessionScope.client}">
                    <li>
                        <a href='<c:url value="/clientNotification"/>'><fmt:message
                                key="client.notifications"/>
                        </a>
                    </li>
                    <li><a href="<c:url value='/clientServing'/>">
                        <fmt:message key="client.orderTaxi"/>
                    </a></li>
                    <li class="dropdown" id="menu">
                        <a class="dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown"
                           href="javascript:void(0)">
                            <c:out value="${client.login}"/>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu" style="left: -30px;">
                            <li role="presentation">
                                <a role="menuitem" tabindex="-1"
                                   href="<c:url value='/clientProfile'/>">
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
                    <li><a href="<c:url value='/login'/>">
                        <fmt:message key="login.button.login"/>
                    </a></li>
                    <li><a href="<c:url value='/register'/>">
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
