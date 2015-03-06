<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="includeFmt.jspf" %>
<fmt:message key="employee.nationalId" var="nationalId"/>
<fmt:message key="employee.phone" var="phone"/>
<t:dispatcherPage title="dispatcher registration">
    <t:registerForm path="/registerDispatcher" param="${param}">
        <jsp:attribute name="moreParamaters">
            <%@include file="employeeParams.jspf" %>
        </jsp:attribute>
    </t:registerForm>
</t:dispatcherPage>
