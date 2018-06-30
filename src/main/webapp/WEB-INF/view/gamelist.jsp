<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<head>
    <title>gamelist</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="Keywords" content="" />
    <meta name="Description" content="" />
    <meta name="author" content="" />
    <meta name="renderer" content="webkit">
    <meta content='target-densitydpi=device-dpi, width=640' name='viewport'>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="white">
    <meta name="format-detection" content="telephone=no">
    <link href="${ctx}/static/css/styles.css" rel="stylesheet">
    <link href="${ctx}/static/css/view.css" rel="stylesheet">
    <link href="${ctx}/static/css/css.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/jquery.page.css" rel="stylesheet">
    <%--<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">--%>
</head>
<script>
    var ctx = '${ctx}';
    /*不同分辨率手机加载*/
    var userScalable = "no";
    var ins = "";
    (function () {
        if (window.devicePixelRatio === 1.5) {
            userScalable = "yes";
            ins = "initial-scale=0.5"
        }
        var text = "<meta content='" + ins + ", target-densitydpi=device-dpi, width=640,user-scalable=" + userScalable + "' name='viewport'>";
        document.write(text);
    })();
</script>
<body ng-app="webApp" ng-controller="game" ng-cloak>
<%--<h2>游戏</h2>--%>
<div ng-view> </div>
</body>
<script type="text/javascript" src="${ctx}/static/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/js/angular/angular.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/js/angular/angular-route.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/websocket.js"></script>
<%--<script type="text/javascript" src="${ctx}/static/assets/tools/common.js"></script>--%>
<script type="text/javascript" src="${ctx}/static/js/jquery.page.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/app/js/game/ctrl.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/app/js/game/service.js"></script>
<script type="text/javascript" src="${ctx}/static/js/global.js"></script>
<script type="text/javascript" src="${ctx}/static/js/plugins/toastr/toastr.min.js"></script>
</html>