var webSocket = null;
var myDate = new Date();
var charTime = myDate.toLocaleTimeString();

//发送消息
function send_msg() {
    //判断是否进入聊天室
    if (webSocket != null) {
        //获取输入信息
        var input_msg = replace_em(document.getElementById("input_msg").value.trim());
        if (input_msg == "") {
            return;
        }
        //发送信息
        webSocket.send(input_msg);
        // 清除input框里的信息
        document.getElementById("input_msg").value = "";
    } else {
        alert("您已掉线，请重新进入聊天室...");
    }
};


//进入聊天室
function initWebSocket() {
    //获得聊天室的房间名
    var roomName = document.getElementById("input_roomName").value;
    // 房间名不能为空
    if (roomName == null || roomName == "") {
        alert("请输入房间名");
        return;
    }
    //获取用户名
    var username = document.getElementById("username").value.trim();
    if (username == "" || username == null) {
        alert("用户名不能为空")
        return;
    }
    //判断浏览器是否支持WebSocket
    if ("WebSocket" in window) {
//            alert("您的浏览器支持 WebSocket!");
        if (webSocket == null) {
            const url = "ws://127.0.0.1:9090/webSocket/chat/" + username + "/" + username;
            // 打开一个 web socket
            webSocket = new WebSocket(url);
        } else {
            alert("您已进入聊天室...");
        }
        webSocket.onopen = function () {
            console.log("已进入聊天室，畅聊吧...");
        };

        webSocket.onmessage = function (evt) {
            // alert(evt.data + "ss");
            var msg_board = document.getElementsByClassName("msg_board")[0];
            //获取聊天内容
            var received_msg = evt.data;
            //获取聊天用户
            var old_msg = msg_board.innerHTML + "<small>" + charTime + "</small>" + "<br>";
            msg_board.innerHTML = old_msg + received_msg + "  " + "<br>";
            // 让滚动块往下移动
            msg_board.scrollTop = msg_board.scrollTop + 80;
        };

        webSocket.onclose = function () {
            // 关闭 websocket，清空信息板
            alert("聊天已关闭...");
            webSocket = null;
            document.getElementsByClassName("msg_board")[0].innerHTML = "";
        };
    } else {
        // 浏览器不支持 WebSocket
        alert("您的浏览器不支持 WebSocket!");
    }
}


//退出聊天室
function closeWs() {
    webSocket.close();
};

$(function(){
    $('.emotion').qqFace({
        id : 'facebox',
        assign:'input_msg',
        path:'arclist/'	//表情存放的路径
    });

    $("#sendMessage").click(function(){
        var str = $("#input_msg").val().trim();
        // $("#show").html(replace_em(str));
        if (str == "" || str == null) {
            return;
        }
        // 发送内容
        var text = replace_em(str);
        // 发送数据
        webSocket.send(text);
        document.getElementsByClassName("msg_board")[0].innerHTML = "";
    });
});

//查看结果
function replace_em(str){
    str = str.replace(/\</g,'&lt;');
    str = str.replace(/\>/g,'&gt;');
    str = str.replace(/\n/g,'<br/>');
    str = str.replace(/\[em_([0-9]*)\]/g,'<img src="arclist/$1.gif" border="0" />');
    return str;
}


(function () {
    /*建立模态框对象*/
    var modalBox = {};
    /*获取模态框*/
    modalBox.modal = document.getElementById("myModal");
    /*获得trigger按钮*/
    modalBox.triggerBtn = document.getElementById("triggerBtn");
    /*获得关闭按钮*/
    modalBox.closeBtn = document.getElementById("closeBtn");
    /*模态框显示*/
    modalBox.show = function () {
        // console.log(this.modal);
        initWebSocket();
        this.modal.style.display = "block";
    }
    /*模态框关闭*/
    modalBox.close = function () {
        closeWs();
        this.modal.style.display = "none";
    }
    /*当用户点击模态框内容之外的区域，模态框也会关闭*/
    modalBox.outsideClick = function () {
        var modal = this.modal;
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    }
    /*模态框初始化*/
    modalBox.init = function () {
        var that = this;
        this.triggerBtn.onclick = function () {
            that.show();
        }
        this.closeBtn.onclick = function () {
            that.close();
        }
        this.outsideClick();
    }
    modalBox.init();

})();