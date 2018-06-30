/**
 * Created by Administrator on 2017/6/24.
 */

var appService = angular.module("gameService", [] );
appService.service('gameService',['$rootScope','$http', function ($rootScope, $http) {
    var service = {
        gameList: function (page, callback) {
            $http({method:"POST",url:ctx+"/game/list",data:page}).success(function (data, status) {
                callback(data);
            })
        },
        getGameContentById: function (prams, callback) {
            $http({method:"POST",url:ctx+"/game/getById",data:prams}).success(function (data, status) {
                callback(data);
            })
        },
        getGameRightChoice: function (prams, callback) {
            $http({method:"POST",url:ctx+"/game/getRightChoice",data:prams}).success(function (data, status) {
                callback(data);
            })
        },
        getGameChoice: function (id, callback) {
            $http({method:"POST",url:ctx+"/game/getGameChoice",data:id}).success(function (data, status) {
                callback(data);
            })
        }
    };
    return service;
}])
