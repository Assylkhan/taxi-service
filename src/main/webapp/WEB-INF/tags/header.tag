<%@include file="../jsp/includeFmt.jspf"%>
<%@tag description="header" %>
<%@attribute name="role"%>
<div class="row aboveHeader container" style="margin:auto;">
    <div class="row">
        <div class="pull-left" style="margin-left: 50px">
            <h2 style="color: #F89406"><fmt:message key="aboveHeader.taxiService"/></h2>

            <div class="custom-yellow"><h3 align=center><fmt:message key="aboveHeader.webOrder"/> <br/>
                <small><fmt:message key="aboveHeader.highQuality"/> </small>
            </h3></div>
        </div>
        <div class="pull-left" style="margin-left: 150px">
            <h1 style="color: #F89406"><strong>777-777</strong></h1>
        </div>
    </div>

    <div class="row" style="margin-bottom: -20px">
        <div class="thumbnail">
            <img style="width: 1200px !important; height: 200px" src="/static/image/taxiForHeader.jpg"
                 alt="taxi image"/>
        </div>
    </div>


</div>
<header class="header">
    <div class="navbar ${role == 'client' ? '' : role=='admin' ? 'navbar-admin': 'navbar-inverse'}">
        <div class="container-fluid">
            <jsp:doBody/>
        </div>
    </div>
</header>