<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeFmt.jspf" %>
<fmt:message key="login.authentication" var="title"/>
<t:adminPage title="${title}">
    <t:loginForm path="adminLogin" role="admin" param="${param}"/>
</t:adminPage>
