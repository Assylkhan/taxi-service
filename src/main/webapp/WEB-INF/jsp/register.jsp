<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.messages"/>
<fmt:message var="registration" key="registration.registration"/>
<t:clientPage title="${registration}">
    <t:registerForm path="/register" param="${param}">
    </t:registerForm>
</t:clientPage>
