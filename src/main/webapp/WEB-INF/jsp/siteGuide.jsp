<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includeFmt.jspf" %>
<fmt:message key="client.guide" var="guide"/>
<t:clientPage title="${guide}">
    <h2 align=center><fmt:message key="client.guide.message"/></h2>
    <div class="horizontalCenter well alert" style="color: #192236">
        <dl>
            <dt>for client</dt>
            <dd>login page url: <a href="<c:url value='/login'/>">/login</a> <br/>
                register page url: <a href="<c:url value='/register'/>">/register</a> </dd>
            <dt>for dispatcher</dt>
            <dd>login page url: <a href="<c:url value='/dispatcherLogin'/>">/dispatcherLogin</a> <br/>
                register page url: <a href="<c:url value='/registerDispatcher'/>">/registerDispatcher</a></dd>
            <dt>for driver</dt>
            <dd>login page url: <a href="<c:url value='/driverLogin'/>">/driverLogin</a> <br/>
                register page url: <a href="<c:url value='/registerDriver'/>">/registerDriver</a></dd>
            <dt>for admin</dt>
            <dd>login page url: <a href="<c:url value='/adminLogin'/>">/adminLogin</a> <br/>
                register page url: <a href="<c:url value='/registerAdmin'/>">/registerAdmin</a></dd>
        </dl>
    </div>

</t:clientPage>
