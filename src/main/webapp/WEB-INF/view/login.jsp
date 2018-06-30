<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<head>
    <title>login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <link href="${ctx}/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/static/css/animate.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/style.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/iconfont.css">
    <link rel="stylesheet" href="${ctx}/static/css/plugins/sweetalert/sweetalert.css">
</head>

</head>
<script>
//    alert();
    var ctx = '${ctx}';
</script>

<body ng-app="webApp" ng-controller="login" ng-cloak>


<div class="middle-box text-center loginscreen">
    <div >
        <div class="animated animated lightSpeedIn ">
            <i class="icon iconfont">&#xf0028;</i>
        </div>
        <form id="userForm" class=" animated rollIn">
            <div class="form-group">
                <input type="text" class="form-control"  placeholder="用户名" ng-model="username" id = "username">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="密码" ng-model="password" id = "password">
            </div>
            <div class="form-group col-xs-6" style="padding-left: 0px;">
                <img src="/getGifCode" ng-click="refreshCode()" id="code">
            </div>
            <div class="form-group col-xs-6">
                <span><input type="text" class="form-control" placeholder="验证码" ng-model="vcode" id = "vcode"></span>
            </div>
            <div class="form-group col-xs-4" style="text-align : left">
                <label><input type="checkbox" id="rememberMe" style="width: 12px; height: 12px;margin-right: 5px;">记住我</label>
            </div>
            <div class="form-group col-xs-8" style="text-align : right">
                <span><a href="${ctx}/register">还没帐号?</a></span>
            </div>
            <button type="submit" class="btn btn-primary block full-width " ng-click="login()" ng-disabled="readonly">登 录</button>
        </form>
        <br/>
        <br/>
        <div class = "animated bounceInLeft">
            © 2017 All Rights Reserved. BlueWhale
        </div>
    </div>
</div>
<div class="part" style="z-index:-1;position:fixed;height:100%;width:100%;top:0;left:0"></div>


<%--<label>用户名:</label><input type="text" ng-model="username"/><br/>
<label>密码:</label><input type="password" ng-model="password"/><br/>
<label>验证码:</label><input type="text" placeholder="验证码" id = "vcode" ng-model="vcode">
<img src="/getGifCode" width="100px" height="30px"><br>
<button ng-click="login()">submit</button>--%>
</body>
<script type="text/javascript" src="${ctx}/static/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/js/angular/angular.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/js/angular/angular-route.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/websocket.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/app/js/login/ctrl.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/app/js/login/service.js"></script>
<script type="text/javascript" src="${ctx}/static/js/global.js"></script>
<script type="text/javascript" src="${ctx}/static/js/plugins/toastr/toastr.min.js"></script>

<script type="text/javascript" src="${ctx}/static/js/plugins/sweetalert/sweetalert.min.js"></script>
</html>