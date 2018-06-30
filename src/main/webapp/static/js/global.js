/**
 * Created by tuchuan on 2017/6/19.
 */
//var server = 'http://172.16.4.139:8080/api/v1';
var sessionid = null;

$toast = {
    init:function(){
        var action = function(){
            toastr.clear();
        };
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "progressBar": false,
            "positionClass": "toast-top-center",
            "onclick": action,
            "showDuration": "100",
            "hideDuration": "100",
            "timeOut": "1500",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }
    },
    success:function(msg){
        if(msg == undefined || msg == null || msg == ''){
            return;
        }
        toastr.clear();
        this.init();
        toastr.success(msg,'温馨提示');
    },
    info:function(msg){
        if(msg == undefined || msg == null || msg == ''){
            return;
        }
        toastr.clear();
        this.init();
        toastr.info(msg,'温馨提示');
    },
    warning:function(msg){
        if(msg == undefined || msg == null || msg == ''){
            return;
        }
        toastr.clear();
        this.init();
        toastr.warning(msg,'温馨提示');
    },
    error:function(msg){
        if(msg == undefined || msg == null || msg == ''){
            return;
        }
        toastr.clear();
        this.init();
        toastr.error(msg,'温馨提示');
    }
}

var $sweetAlert = {
    show: function (type, arr, callback) {
        switch (type) {
            case 2://用于仅仅需要提示的对话框
                sweetAlert(arr[0], arr[1]);
                break;
            case 3://用于提示操作成功的对话框
                swal({
                        title: arr[0],
                        text: arr[1],
                        type: "success"
                    },
                    function () {
                        if (callback != undefined) {
                            callback();
                        }
                    });
                break;
            case 4://用于提示操作失败的对话框
                swal({
                        title: arr[0],
                        text: arr[1],
                        type: "warning"
                    },
                    function () {
                        if (callback != undefined) {
                            callback();
                        }
                    });
                break;
            case 5://用于需要确认的对话框
                swal({
                        title: arr[0],
                        text: arr[1],
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: arr[2],
                        cancelButtonText: "取消",
                        closeOnConfirm: true
                    },
                    function (flag) {
                        if (callback != undefined) {
                            callback(flag);
                        }
                    });
                break;
            case 6://用于需要确认的对话框,可设置type
                swal({
                        title: arr[0],
                        text: arr[1],
                        type: arr[2],
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "Yes!",
                        closeOnConfirm: true
                    },
                    function () {
                        if (callback != undefined) {
                            callback();
                        }
                    });
                break;
            case 7://用于自定义弹窗html内容
                swal({
                        title: arr[0],
                        text: arr[1],
                        html: true,
                        type:"success",
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "取消",
                        closeOnConfirm: true
                    },
                    function () {
                        if (callback != undefined) {
                            callback();
                        }
                    });
                break;
        }
    }
}

function getQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return unescape(r[2]); return null;
}

function getSessionId(){
    if (window.sessionStorage) {
        var storage=window.sessionStorage;
        return storage.getItem("sessionid");
    }
}

function getOnlineCount() {
    if (window.sessionStorage) {
        var online = window.sessionStorage.getItem("online");
        var uid = window.sessionStorage.getItem("uid");
        if ((uid != null && uid != undefined)) {
            if (online == null || online == undefined) {
                window.sessionStorage.setItem("online", true);
                try {
                    $.ajax({
                        type: "post",
                        url: "sendMessage",
                        data: {message : " "},
                        dataType: "json",
                        success: function (r) {
                        },
                        error: function (e) {
                        }
                    });
                } catch (e){}
            }
        }
    }
}

function clearLogin() {
    if (window.sessionStorage) {
        window.sessionStorage.clear("online");
    }
}

var identityTypes = [
    {"key":"email","value":"邮箱"},
    {"key":"phone","value":"手机"},
];