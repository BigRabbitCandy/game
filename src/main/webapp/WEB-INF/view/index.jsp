<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<head>
    <title>index</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${ctx}/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">
</head>
<script>
    var ctx = '${ctx}';
    location.href= ctx + "/game";
</script>
<%--<body ng-app="webApp" ng-controller="login" ng-cloak>
<label>用户名:</label><input type="text" ng-model="message"/><br/>
<input type="file" id="file"/><br>
<button ng-click="sendMessage()">submit</button>--%>
</body>
<script type="text/javascript" src="${ctx}/static/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/js/angular/angular.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/js/angular/angular-route.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/app/js/login/ctrl.js"></script>
<script type="text/javascript" src="${ctx}/static/js/websocket.js"></script>
<script type="text/javascript" src="${ctx}/static/js/global.js"></script>
<script type="text/javascript" src="${ctx}/static/js/plugins/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/app/js/login/service.js"></script>
</html>