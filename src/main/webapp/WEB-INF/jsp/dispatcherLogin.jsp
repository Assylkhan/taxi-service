<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="includeFmt.jspf"%>
<fmt:message key="login.authentication" var="title"/>
<t:dispatcherPage title="${title}">
    <t:loginForm path="dispatcherLogin" role="dispatcher" param="${param}"/>
</t:dispatcherPage>
