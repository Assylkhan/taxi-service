<%@tag description="login form" %>
<%@attribute name="path" required="true" %>
<%@attribute name="role" required="true" %>
<%@attribute name="loginError" %>
<%@attribute name="param" required="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/WEB-INF/jsp/includeFmt.jspf"%>
<fmt:message key="user.login" var="login"/>
<fmt:message key="user.password" var="password"/>
<fmt:message key="login.button.login" var="buttonLogin"/>
<form class="loginForm alert alert-info col-md-5 horizontalCenter"
      action="<c:url value='/login'/>" method="post">
    <c:if test="${not empty loginError}">
        <strong class="alert-danger">
            <fmt:message key="${loginError}"/>
        </strong><br/></c:if>
    <br/>
    <input type="hidden" name="path" value="${path}">
    <input type="hidden" name="role" value="${role}">
    <t:input type="text" placeholder="${login}" value="${param.login}" name="login"/>
    <t:input type="password" placeholder="${password}" value="${param.password}" name="password"/>
    <t:input className="btn btn-info" value="${buttonLogin}" type="submit"/>
</form>