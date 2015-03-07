<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeFmt.jspf" %>

<t:driverPage title="driver profile">
    <div class="well">
        <strong style="color: darkred">
            <c:if test="${not empty changeResult}">
                <fmt:message key="${changeResult}"/>
            </c:if>
        </strong><br/><br/>

        <p><strong><a href='<c:url value="/changeDriverState"/>'>
            <fmt:message key="driver.state.change"/>
        </a></strong></p>
        <dl>
            <dt><fmt:message key="user.firstName"/></dt>
            <dd>${driver.firstName}</dd>

            <dt><fmt:message key="user.lastName"/></dt>
            <dd>${driver.lastName}</dd>

            <dt><fmt:message key="driver.message.currentLocation"/></dt>
            <dd>
                <fmt:message key="driver.location.undefined" var="undefined"/>
                <c:if test="${driver.currentLocation == null || driver.currentLocation == ''}">
                    ${undefined}
                </c:if>
                    ${driver.currentLocation}
            </dd>

            <dt><fmt:message key="driver.message.availability"/></dt>
            <dd>
                <fmt:message key="driver.available.yes" var="yes"/>
                <fmt:message key="driver.available.no" var="no"/>
                    ${driver.available == true ? yes : no}
            </dd>

            <dt><fmt:message key="user.login"/></dt>
            <dd>${driver.login}</dd>
        </dl>
    </div>

</t:driverPage>
