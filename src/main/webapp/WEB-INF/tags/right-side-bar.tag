<%@tag description="Right Side Bar" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/includeFmt.jspf" %>
<div class="right-side-bar">
    <div role="tabpanel">
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active">
                <a href="#news" aria-controls="news" role="tab"
                   data-toggle="tab"><span class="glyphicon glyphicon-globe"></span>
                    <fmt:message key="announcement.news"/> </a></li>
            <li role="presentation">
                <a href="#comments" aria-controls="comments" role="tab"
                   data-toggle="tab"><span class="glyphicon glyphicon-comment"></span>
                    <fmt:message key="comment.pageTitle"/></a></li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="news">
                <c:forEach items="${recentAnnouncements}" var="announcement">
                    <div>
                        <a href="<c:url value='/announcements'/>">
                            <p>
                                    ${empty announcement.bodyEn ? announcement.bodyRu : announcement.bodyEn}
                            </p>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <div role="tabpanel" class="tab-pane" id="comments">
                <c:forEach items="${recentComments}" var="comment">
                    <div>
                        <p>
                            <span style="color: #192236">@${comment.client.login} -
                            <fmt:formatDate value="${comment.leaveDate}" pattern="dd/MM/yyyy HH:mm"/></span>
                                ${comment.body}</p>
                    </div>
                </c:forEach>
                <a href='<c:url value="/comments"/>'>
                    <fmt:message key="comments.showAll"/>
                </a>
            </div>
        </div>
    </div>
</div>
