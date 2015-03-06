<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="includeFmt.jspf" %>

<t:dispatcherPage title="dispatcher profile">

    <div class="well">
        <dl class="horizontalCenter">
            <dt><fmt:message key="user.firstName"/></dt>
            <dd>${dispatcher.firstName}</dd>

            <dt><fmt:message key="user.lastName"/></dt>
            <dd>${dispatcher.lastName}</dd>

            <dt><fmt:message key="user.login"/></dt>
            <dd>${dispatcher.login}</dd>

            <dt><fmt:message key="employee.nationalId"/></dt>
            <dd>${dispatcher.nationalId}</dd>
        </dl>
    </div>

</t:dispatcherPage>
