<%@tag description="register form tag" %>
<%@attribute name="path" required="true" %>
<%@attribute name="param" required="true" %>
<%@attribute name="moreParamaters" fragment="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@include file="/WEB-INF/jsp/includeFmt.jspf" %>
<fmt:message var="firstName" key="user.firstName"/>
<fmt:message var="lastName" key="user.lastName"/>
<fmt:message var="login" key="user.login"/>
<fmt:message var="password" key="user.password"/>
<fmt:message var="confirmPassword" key="registration.confirmPassword"/>
<fmt:message var="signup" key="registration.signup"/>
<form class="registerForm alert alert-success col-lg-5 horizontalCenter"
      action='<c:url value="${path}" />' method="post">
    <t:input type="text" placeholder="${firstName}" name="firstName"
             value="${param.firstName}" fieldError="${firstNameError}"/>
    <t:input type="text" placeholder="${lastName}" name="lastName"
             value="${param.lastName}" fieldError="${lastNameError}"/>
    <t:input type="text" placeholder="${login}" name="login"
             value="${param.login}" fieldError="${loginError}"/>
    <t:input type="password" placeholder="${password}" name="password"
             value="${param.password}" fieldError="${passwordError}"/>
    <t:input type="password" placeholder="${confirmPassword}" name="confirmPassword"
             value="${param.confirmPassword}" fieldError="${confirmError}"/>
    <jsp:invoke fragment="moreParamaters"/>
    <t:input type="submit" className="btn btn-danger" value="${signup}"/>
</form>
