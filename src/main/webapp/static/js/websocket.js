var websocket = null;
var uid = null;
if (window.sessionStorage) {
    var storage = window.sessionStorage;
    if (storage.getItem("websocket") != undefined || storage.getItem("websocket") != null || storage.getItem("websocket") != "") {
        websocket = storage.getItem("websocket");
    }

    if (storage.getItem("uid") != undefined || storage.getItem("uid") != null || storage.getItem("uid") != "") {
        var uid = storage.getItem("uid");
    }
}


var connectCount = 0;

//重连出错 时间间隔
var connecttime = new Array(0.1,0.2,0.2,5,0.2,7,0.2,7,0.1,10,0.2);

if (uid != null) {
    connect(uid);
}

var num;


function connect(uid){


    var timeoutProcess;

    connectCount++;
    //console.info("--------------"+new Date().toLocaleDateString());
    //判断当前浏览器是否支持WebSocket
    if('WebSocket' in window){
        //uid在home.html页面定义
        try{
            if (window.sessionStorage) {
                var storage=window.sessionStorage;
                storage.setItem("uid",uid);
            }

            //随机生成uid
            num = Math.random().toFixed(2)*100;

            console.info("-------"+window.location.host+"---"+num);

            websocket = new WebSocket("ws://"+window.location.host+"/admin/websocket/"+uid);

            if (window.sessionStorage) {
                var storage=window.sessionStorage;
                storage.setItem("websocket",websocket);
            }

        }catch(e){
            console.info("系统繁忙！");
        }
    }
    else{
        console.info("--------Not support websocket---------------");
    }

    //连接发生错误的回调方法
    websocket.onerror = function(){
        console.info("===========链接失败-----------");

        var sleepTime = Number(connecttime[(connectCount-1)])*60000;
        if(connectCount<=10){

            //console.info("connectCount"+sleepTime+"开始计时");

            this.timeoutProcess = setTimeout("connect()",parseInt(sleepTime));

            clearTimeout(this.timeoutObj);
            //console.info("结束计时");

        }else{
            this.timeoutProcess = setTimeout("connect()",parseInt(sleepTime));
            connectCount = 0;
        }

    };

    //连接成功建立的回调方法
    websocket.onopen = function(event){
        connectCount = 0;
        //心跳计时
        heartCheck.start();
    }

    //接收到消息的回调方法
    websocket.onmessage = function(event){
        var msg = event.data
        if(msg.indexOf("HeartBeat")==-1){
            setMessageInnerHTML(msg);
        }

        //重置计时
        heartCheck.reset();
    }

    //连接关闭的回调方法
    websocket.onclose = function(){
        /*connect();*/

    }
}


//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function(){
    websocket.close();
}

//将消息显示在网页上
function setMessageInnerHTML(innerHTML){
    $toast.info(innerHTML);
    windowsNotify(innerHTML);
}

//关闭连接
function closeWebSocket(){
    websocket.close();
}

//发送消息
function send(){
    /*        var message = document.getElementById('text').value;
     websocket.send(message);*/
}

/*
 * 桌面通知
 * strNewsContent:通知的内容
 */
function windowsNotify(strNewsContent) {
    if (!("Notification" in window) && !window.webkitNotifications && window.webkitNotifications.checkPermission() != 0)
        return;

    if (Notification.permission == null || Notification.permission == undefined)
        windowsNotify360(strNewsContent);
    else if (Notification.permission === "granted")
        windowsNotifyFFAndGE(strNewsContent);
    else if (Notification.permission !== 'denied') {
        Notification.requestPermission(function (permission) {
            if (!('permission' in Notification))
                Notification.permission = permission;

            if (permission === "granted")
                windowsNotifyFFAndGE(strNewsContent);
        });
    }
}

//桌面通知(兼容360)
function windowsNotify360(strNewsContent) {
    if (window.webkitNotifications && window.webkitNotifications.checkPermission() == 0) {
        var notify = window.webkitNotifications.createNotification(
            "http://localhost/static/assets/img/06.jpg",
            '后台-消息中心',
            strNewsContent
        );

        //设置定时撤销机制，防止通知长时间显示不被关闭
        notify.ondisplay = function (event) {
            setTimeout(function () {
                event.currentTarget.cancel();
            }, 10000);
        };
        //下面是定义点击事件，类似地还可定义其它事件
        notify.onclick = function () {
            window.focus();
            notification.close();
        };
        //弹出
        notify.show();
    } else if (window.webkitNotifications) {
        window.webkitNotifications.requestPermission(windowsNotify360);
    }
}


//桌面通知(兼容火狐、谷歌)
function windowsNotifyFFAndGE(strNewsContent) {
    var notification = new Notification('后台-消息中心',
        {
            body: strNewsContent,
            icon: "http://localhost/static/assets/img/06.jpg"
        });

    //设置定时撤销机制，防止通知长时间显示不被关闭
    notification.ondisplay = function (event) {
        setTimeout(function () {
            event.currentTarget.cancel();
        }, 150000);
    };

    //下面是定义点击事件，类似地还可定义其它事件
    notification.onclick = function () {
        window.focus();
        notification.close();
    };
    console.log(notification);
}


//做心跳检测
var heartCheck = {
    timeout: 60000,//60ms
    timeoutObj: null,
    reset: function(){
        clearTimeout(this.timeoutObj);
        this.start();
    },
    start: function(){
        this.timeoutObj = setTimeout(function(){
            console.info("---------发心跳包----------");
            websocket.send(uid+",HeartBeat");
        }, 60000)
    }
}
    