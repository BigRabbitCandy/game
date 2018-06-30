<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<head>
    <title>register</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${ctx}/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
</head>
<script>
//    alert();
    var ctx = '${ctx}';
</script>
<style>
    table{border-collapse: collapse;}
   /* td{padding:5px;}*/
    tr {
        display: block; /*将tr设置为块体元素*/
        margin: 2px 0; /*设置tr间距为2px*/
    }
</style>
<body ng-app="webApp" ng-controller="register" ng-cloak>
<div style="margin:0 auto;width:280px;margin-top:100px">
<table style="margin:auto;">
    <tr style="margin-bottom: 10px">
        <td><select ng-model="user.identityType" ng-disabled="true" ng-options="i.key as i.value for i in identityTypes" title="暂不支持其他类型" ng-click="show()"></select> <button class="btn btn-default" id="code" ng-bind="code" ng-click="sendCode()"></button></td>
    </tr>
    <tr>
        <td><input type="text" placeholder="邮箱" ng-model="user.identifier"/></td>
    </tr>
    <tr>
        <td><input type="password" placeholder="密码" ng-model="user.credential"/></td>
    </tr>
    <tr>
        <td><input type="text" placeholder="验证码" id = "vcode" ng-model="user.vcode"></td>
    </tr>
    <tr align="center">
        <td colspan="2"><button class="btn btn-primary block full-width" ng-click="register()" ng-disabled="readonly">register</button></td>
    </tr>
</table>
</div>
</body>
<script type="text/javascript" src="${ctx}/static/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/js/angular/angular.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/js/angular/angular-route.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/websocket.js"></script>
<script type="text/javascript" src="${ctx}/static/js/global.js"></script>
<script type="text/javascript" src="${ctx}/static/js/plugins/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/app/js/register/ctrl.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/app/js/register/service.js"></script>
</html>