/**
 * Created by Administrator on 2017/6/24.
 */
angular.module('webApp',['ngRoute','registerService'])
    .controller('register',['$scope','$route','$routeParams','$interval','registerService', function (scope, route, routeParams,interval, registerService) {
        scope.ctx = ctx;
        scope.page = new Object();
        scope.user = new Object();
        scope.identityTypes = identityTypes;
        scope.code = "发送验证码";
        scope.user.vcode = "";
        scope.user.identityType = "email";
        scope.userInfo = new Object();
        scope.readonly = false;

        scope.show = function () {
            $toast.info("暂不支持其他类型");
        }

        scope.register = function () {
            if (scope.user.identifier==undefined || scope.user.identifier==null || scope.user.identifier=="") {
                $toast.info("请填写邮箱");
                return;
            }
            if (scope.user.credential==undefined || scope.user.credential==null || scope.user.credential=="") {
                $toast.info("请填写密码");
                return;
            }

            if (scope.user.vcode==undefined || scope.user.vcode==null || scope.user.vcode=="") {
                $toast.info("请填写验证码");
                return;
            }
            scope.readonly = true;
            registerService.ajaxRegister(scope.user, function (data) {
                clearLogin();
                if (data.status == 'OK') {
                    scope.userInfo = data.message.userInfo;
                    var uid = scope.userInfo.uid;
                    if (window.sessionStorage) {
                        var storage=window.sessionStorage;
                        storage.setItem("sessionid",data.message.sessionid);
                    }
                    if (window.sessionStorage) {
                        sessionStorage.setItem("uid", uid)
                    }
                    connect(uid);
                    if (window.sessionStorage) {
                        sessionStorage.setItem("uid", uid)
                    }
                    // setTimeout(function () {
                        location.href =ctx+"/game";
                    // },1500);
                }else{
                    $toast.info(data.message);
                    scope.readonly = false;
                }
            })
        };

        var second = 60,timePromise = undefined;


        scope.sendCode = function () {
            if (scope.user.identifier==undefined||scope.user.identifier==null||scope.user.identifier=="") {
                $toast.info("请填写邮箱");
                return;
            }
            $("#code").attr("disabled",true);
            scope.code = second + "秒后可重发";
            registerService.sendCode(scope.user.identifier, function (data) {
                if (data.status == 'ERROR') {
                    $toast.info(data.message);
                }else if (data.status = 'OK') {
                    $toast.success("邮件发送成功！");
                }
             });
            timePromise = interval(function(){
                if(second<=0){
                    interval.cancel(timePromise);
                    timePromise = undefined;
                    second = 60;
                    scope.code = "重发验证码";
                    $("#code").attr("disabled",false);
                }else{
                    scope.code = (second - 1) + "秒后可重发";
                    second--;

                }
            },1000,1000);
        }
    }]);