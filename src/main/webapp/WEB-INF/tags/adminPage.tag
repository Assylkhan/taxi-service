<%@tag description="Client Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/WEB-INF/jsp/includeFmt.jspf" %>
<%@attribute name="title" %>
<t:genericPage title="${title}" role="admin">
    <jsp:attribute name="header">
        <t:languageToggler/>
        <h2 class="horizontalCenter navbar-center">
            <fmt:message key="admin"/>
        </h2>
        <ul class="list-unstyled pull-right">
            <c:choose>
                <c:when test="${not empty sessionScope.admin}">
                    <li>
                        <a href='<c:url value="/reports"/>'><fmt:message
                                key="admin.reports"/>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/addAnnouncement'/>">
                            <fmt:message key="admin.addAnnouncement"/>
                        </a>
                    </li>
                    <li class="dropdown" id="menu">
                        <a href="<c:url value='/logout'/>">
                            <fmt:message key="login.logout"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value='/adminLogin'/>">
                        <fmt:message key="login.button.login"/>
                    </a></li>
                    <li><a href="<c:url value='/registerAdmin'/>">
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
