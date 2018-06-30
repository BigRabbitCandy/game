/**
 * Created by Administrator on 2017/6/24.
 */
angular.module('webApp',['ngRoute','gameService'])


    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/list',{
                templateUrl:ctx+'/static/assets/app/html/game/gameList.html',
                controller:'game'
            })
            .when('/game/:id',{
                templateUrl:ctx+'/static/assets/app/html/game/gameDetails.html',
                controller:'game'
            })
            .otherwise({redirectTo:'/list'});
    }])
    .controller('game',['$scope','$route','$routeParams','gameService', function (scope, route, routeParams, gameService) {
        scope.ctx = ctx;
        scope.page = new Object();
        scope.init = 1;
        scope.pageNo = 1;
        scope.games = null;
        scope.gameContent = null;
        scope.gameChoice = null;
        scope.next = null;
        scope.gameId = null;
        scope.contentShow = null;
        scope.answer = null;
        scope.error = null;
        scope.index = 0;
        scope.totals = 1;
        scope.pages = 0;
        var pages = 0;
        scope.gameList = function (pageNo) {
            if (pageNo != undefined && pageNo != null && !isNaN(pageNo)) {
                scope.pageNo = pageNo;
            }
            scope.page.current = scope.pageNo;
            gameService.gameList(scope.page, function (data) {
                if (data.status == 'OK') {
                    var page = data.message;
                    scope.page = page;
                    scope.games = scope.page.records;
                    if (scope.page.pages==0) {
                        scope.pages = scope.page.total / scope.page.size;
                    }else{
                        scope.pages = scope.page.pages;
                    }
                    pages = scope.pages;
                    if (scope.init == 1) {
                        $("#page").Page({
                            totalPages:  pages,//分页总数
                            liNums: 3,//分页的数字按钮数(建议取奇数)
                            activeClass: 'activP', //active 类样式定义
                            callBack : function(page){
                                //console.log(Math.ceil(scope.page.total/scope.page.size));
                                scope.toPage(page);
                            }
                        });
                        scope.init = scope.init + 1;
                    }
                    /*getPageArr(page.pages,page.total, function (pagesArr) {
                        scope.pagesArr = pagesArr;
                    })*/
                }
            });
            setTimeout(function () {
                getOnlineCount();
            },5000);
        };



        scope.toPage = function(p){
            scope.gameList(p);
        }
        scope.run = function (id) {
            scope.answer = null;
            scope.next = null;
            scope.index = scope.index+ 1;
            scope.gameId = routeParams.id;
            var parms = new Object();
            parms = {"id":scope.gameId,"sessionid":getSessionId()};
            parms.gameCountId = id;
            // var parms = {"id":scope.gameId,"gameCountId":id};
            gameService.getGameContentById(parms, function (data) {
                if (data.status == "OK") {
                    scope.contentShow = 1;
                    scope.gameContent = data.message;
                    var choiceId = scope.gameContent.choiceid;
                    if (choiceId == 0) {
                        scope.next = 1;
                    }else {
                        scope.getGameChoiceById(scope.gameContent.choiceid);
                    }
                }else {
                    $toast.info("请先登录");
                    setTimeout(function () {
                        location.href = ctx+"/login";
                    },2000);
                }
            });
        };
        scope.getGameChoiceById = function (id) {
            gameService.getGameChoice({"id":id,"sessionid":getSessionId()},function (data) {
                if (data.status == "OK") {
                    scope.gameChoice = data.message;
                    if (scope.gameChoice!=null) {
                        scope.choices = eval(scope.gameChoice.choice);
                    }
                }else{
                    location.href = ctx;
                }
            })
        }
        scope.runNext = function (id,choice) {
            scope.choices ="";
            var parms = {"id":id,"choice":choice,"sessionid":getSessionId()};
            gameService.getGameRightChoice(parms,function (data) {
                if (data.status == "OK") {
                    scope.contentShow = null;
                    scope.next = 1;
                    scope.gameChoice = null;
                    scope.answer = data.message;
                    var jump = data.extend;
                    if (jump == 1) {
                        scope.index = scope.index+ 1;
                        scope.gameContent.id = scope.gameContent.id + 1;
                    }
                }else if (data.status == "ERROR") {
                    scope.answer = data.message;
                    scope.gameContent = null;
                    scope.gameChoice = null;
                    scope.contentShow = null;
                    scope.error = 1;
                }
            });
        }

        scope.nextContent = function (id) {
            scope.run(id+1);
        }

        scope.home = function () {
            location.href = ctx +"/game";
        }


    }])