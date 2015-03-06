<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/includeFmt.jspf" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" required="true" %>
<%@attribute name="role" %>
<c:set var="locale"
       value="${not empty param.language ? param.language : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<html lang="${locale}">
<head>
    <link rel="stylesheet" href='<c:url value="/webjars/bootstrap/3.3.1/css/bootstrap.css"/>'>
    <script src='<c:url value="/webjars/jquery/2.1.3/jquery.js"/>'></script>
    <script src='<c:url value="/webjars/bootstrap/3.3.1/js/bootstrap.js"/>'></script>
    <script src='<c:url value="/static/js/application.js"/>'></script>

    <link rel="stylesheet" href="/static/css/main.css">
    <%--<link rel="icon" href='<c:url value="/static/image/logoTitle.ico"/>' type="image/x-icon">--%>
    <title>${title}</title>
</head>
<body id="${role}">
<div class="own-container" id="parent-wrapper">
    <t:header role="${role}">
        <jsp:invoke fragment="header"/>
    </t:header>

    <div id="wrapper" class="row">
        <div class="content">
            <jsp:doBody/>
        </div>
        <t:right-side-bar/>
    </div>
    <%--<div class="push"></div>--%>
    <footer class="footer">
        <div class="navbar ${role == 'client' ? '' : role=='admin' ? 'navbar-admin': 'navbar-inverse'}">
            <div class="container-fluid" style="margin: 15px auto">
                <div class="horizontalCenter" style="font-size: 16px; text-align: center;">
                    © 2015 <fmt:message key="footer.assylkhanRakhatov"/> | 777-777

                </div>
            </div>
        </div>
    </footer>
</div>
</body>
</html>
