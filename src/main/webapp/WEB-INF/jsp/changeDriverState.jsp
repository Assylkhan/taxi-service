<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeFmt.jspf" %>
<fmt:message key="driver.pageTitle.changeCurrentState" var="title"/>
<t:driverPage title="${title}">
    <div class="well">
        <form style="margin: 70px auto; float: none" class="alert alert-info col-md-5"
              action='<c:url value="/changeDriverState"/>' method="post">
            <strong style="color: darkred">${changeResult}</strong><br/>

            <div class="row">
                <div class="form-group col-md-10 checkbox">
                    <label>
                        <input type="checkbox" name="available"/><fmt:message key="driver.message.availability"/>
                    </label>
                </div>
            </div>

            <fmt:message key="driver.message.currentLocation" var="location"/>
            <fmt:message key="driver.changeState.update" var="update"/>
            <t:input type="text" name="location" placeholder="${location}"/>
            <t:input type="submit" value="${update}" className="btn btn-info"/>
        </form>
    </div>
</t:driverPage>
