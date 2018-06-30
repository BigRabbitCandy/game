/**
 * Created by Administrator on 2017/6/24.
 */
angular.module('webApp',['ngRoute','loginService'])
    .controller('login',['$scope','$route','$routeParams','loginService', function (scope, route, routeParams, loginService) {
        /*try {
            closeWebSocket();
        } catch (e) {

        }*/
        scope.ctx = ctx;
        scope.page = new Object();
        scope.username = "";
        scope.password = "";
        scope.vcode = "";
        scope.rememberMe = $('#rememberMe').is(':checked');
        scope.userInfo = new Object();
        scope.readonly = false;
        scope.login = function () {
            scope.readonly = true;
            var param = {"username":scope.username,"password":scope.password,"rememberMe":scope.rememberMe,"vcode":scope.vcode};
            loginService.ajaxLogin(param, function (data) {
                if (data.status == 'OK') {
                    clearLogin();
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
                    // setTimeout(function () {
                        location.href =ctx+"/game";
                    // },1500);
                    /*scope.sendMessage(function (data) {
                        if (data.status == "OK") {
                            setTimeout(function () {
                                location.href =ctx+"/game";
                            },1500);
                        }else{
                            location.href =ctx+"/game";
                        }
                    });*/

                }else {
                    $toast.info(data.message);
                    scope.readonly = false;
                }
            })
        }

        $("#file").change(function () {
            /*if(!(/(?:jpg|bmp|png|jpeg)$/i.test(this.value))){
                $toast.warning("请上传jpg,bmp,png,jpeg格式文件");
                return;
            }*/
            var file = $(this)[0].files[0];
            loginService.upload(file, function (data) {
                $toast.info(data.message);
            });
        });


        scope.message = " ";
        scope.sendMessage = function (callback) {
            loginService.sendMessage(scope.message, function (data) {
                if(callback){
                    callback(data);
                }
            });
        }

        scope.refreshCode = function () {
            $("#code").attr("src",'/getGifCode?r='+Math.random());
        }
    }]);