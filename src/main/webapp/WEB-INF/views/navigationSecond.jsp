<%@ page pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Navigation</title>
    <link href="/css/bootstrap.css" rel='stylesheet' type='text/css' />
    <link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>" target="_top">
</head>
<body>

<div class="navbar  navbar-default" role="navigation" style="margin-bottom:50px;background-color:#2c333d; ">
	<div class="col-md-9" >
	<div class="navbar-header">
	<a href="index" class="navbar-brand">XTUOJ</a>
	  　</div>
    <ul class="nav navbar-nav" style="font-size:18px;margin:20px auto;text-align:center;style="color:white;">
        <li><a href="index" style="color:white; ">Home</a></li>
      	<li><a href="problem/problems/0" style="color:white;">Problem Set</a></li>
      	<li><a href="ranklist/0" style="color:white;">Ranklist</a></li>
        <li><a href="status/0" style="color:white;">Status</a></li>
      	<li class="dropdown"><a href="test/test"style="color:white;">Contest</a></li>

    </ul>
   </div>
    <security:authentication property="principal.username" var="userId"/>
    <div class="col-md-3 logBtn" style="width:200px;float:right;padding-top:25px;">
        <c:if test="${userId ne null}">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle btn btn-primary" data-toggle="dropdown"
                   style="width: 160px;">${userId} zhuyi<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Self Info</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Logout</a></li>
                </ul>
            </li>
        </c:if>
        <c:if test="${userId eq null}">
            <a href="/login" class="dropdown-toggle btn btn-primary" style="width: 160px;">
                Login
            </a>
        </c:if>
    </div>

</div>
</div>
</body>
</html>