<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="includeFmt.jspf" %>
<fmt:message key="client.clientProfile" var="clientProfile"/>
<t:clientPage title="client profile">

    <div class="well alert-info">
        <dl class="dl" style="text-align: center;">
            <p>${insertApp}</p><br/>

            <dt><fmt:message key="user.firstName"/></dt>
            <dd>${client.firstName}</dd>

            <dt><fmt:message key="user.lastName"/></dt>
            <dd>${client.lastName}</dd>

            <dt><fmt:message key="user.login"/></dt>
            <dd>${client.login}</dd>
            <script>$('#bonusHint').tooltip();</script>
            <dt><fmt:message key="client.profile.bonus"/></dt>
            <fmt:message var="tenge" key="client.bonus.currency"/>
            <fmt:message var="hint" key="client.bonus.title"/>
            <dd >${client.bonus} ${tenge}
                <span class="glyphicon glyphicon-info-sign" data-toggle="tooltip"
                   title="${hint}" id='bonusHint'></span>
            </dd>
                <%--<fmt:formatNumber value="${client.bonus}" type="currency"/>--%>
        </dl>
    </div>

</t:clientPage>
