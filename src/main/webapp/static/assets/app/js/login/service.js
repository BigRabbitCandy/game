/**
 * Created by Administrator on 2017/6/24.
 */

var appService = angular.module("loginService", [] );
appService.service('loginService',['$rootScope','$http', function ($rootScope, $http) {
    var service = {
        ajaxLogin: function (map, callback) {
            $http({method:"POST",url:ctx+"/ajaxLogin",data:map}).success(function (data, status) {
                callback(data);
            })
        },
        sendMessage: function (message,callback) {
            $http({method:"POST",url:ctx+"/sendMessage",data:message}).success(function (data,status) {
                callback(data);
            })
        },
        upload: function (file) {
            $http({method:"POST",url:ctx+"/upload",
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: function () {
                    var formData = new FormData();
                    formData.append('file', file);
                    return formData;
                }
            }).success(function (data,status) {
                callback(data);
            })
        },
    };
    return service;
}])
