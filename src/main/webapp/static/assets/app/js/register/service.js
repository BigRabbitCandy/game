/**
 * Created by Administrator on 2017/6/24.
 */

var appService = angular.module("registerService", [] );
appService.service('registerService',['$rootScope','$http', function ($rootScope, $http) {
    var service = {
        ajaxRegister: function (user, callback) {
            $http({method:"POST",url:ctx+"/register/register",data:user}).success(function (data, status) {
                callback(data);
            })
        },
        sendCode: function (to, callback) {
            $http({method:"POST",url:ctx+"register/sendMail",data:to}).success(function (data,status) {
                callback(data);
            })
        }
    };
    return service;
}])
