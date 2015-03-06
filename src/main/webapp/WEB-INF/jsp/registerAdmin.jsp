<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeFmt.jspf" %>
<fmt:message key="registration.register" var="registration"/>
<t:adminPage title="${registration}">
    <t:registerForm path="/registerAdmin" param="${param}">
    </t:registerForm>
</t:adminPage>