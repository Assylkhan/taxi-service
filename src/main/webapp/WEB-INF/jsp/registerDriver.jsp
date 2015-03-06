<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="includeFmt.jspf"%>
<fmt:message key="employee.nationalId" var="nationalId"/>
<fmt:message key="employee.phone" var="phone"/>
<fmt:message key="driver.carGovNumber" var="govNumber"/>
<fmt:message key="driver.carClass" var="carClass"/>
<fmt:message key="driver.carModel" var="carModel"/>
<c:if test="${not empty error}">
    <fmt:message key="${error}" var="natIdError"/>
</c:if>
<t:dispatcherPage title="driver registration">
    <t:registerForm path="/registerDriver" param="${param}">
        <jsp:attribute name="moreParamaters">
            <%@include file="employeeParams.jspf"%>
            <t:input type="text" placeholder="${govNumber}"
                     name="govNumber" fieldError="${govNumberError}" value="${param.govNumber}"/>
            <div class="row">
                <div class="form-group col-lg-12">
                    <label>${carClass}</label>
                    <select class="form-control" name="carClass">
                        <option value="VIP">vip</option>
                        <option value="USUALLY"><fmt:message key="client.carType.usually"/></option>
                        <option value="ECONOMY"><fmt:message key="client.carType.economy"/></option>
                    </select>
                </div>
            </div>
            <t:input type="text" placeholder="${carModel}" name="carModel" value="${param.carModel}"/>
        </jsp:attribute>
    </t:registerForm>
</t:dispatcherPage>
