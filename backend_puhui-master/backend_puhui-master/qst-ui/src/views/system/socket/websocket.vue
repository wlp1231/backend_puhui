<template>
  <el-container>
    <el-aside width="200px">
      <ul style="height: 450px; overflow: auto;">
        <li class="chartComName"  v-for="item in companiesName" @click="chartClick(item.comPhone)" style="list-style: none; margin-left: -30px; margin-top: 10px; border:1px solid #d7dae2 ;border-radius: 2px; padding: 5px;">{{ item.comName }}<br><small>{{item.comPhone}}</small></li>
      </ul>
    </el-aside>
    <el-main>
      <div class="app-container">
        <el-input v-show="false" v-model="url" type="text" style="width: 20%" /> &nbsp; &nbsp;
        <el-button @click="join" type="primary">连接</el-button>
        <el-button @click="exit" type="danger">断开</el-button>
        <!--    <el-input class="msg_board" type="textarea" v-model="text_content" :rows="9" />-->
        <div class="msg_board" style="overflow: auto; height: 200px; border: 1px solid #eee; margin-top: 20px">
        </div>
        <br />
        <br />
        <el-input type="textarea" v-model="message" :rows="2" />
        <el-button type="info" @click="send" style="margin-top: 20px">发送消息</el-button>
        <br />
        <br />
      </div>
    </el-main>
  </el-container>

</template>

<script>
import {getAptitudeCompanyName} from "@/api/system/aptitude";

export default {
  name: "websocket",
  data() {
    return {
      // url: "ws://127.0.0.1:9090/websocket/message",
      url: "ws://127.0.0.1:9090/webSocket/chat/1099/客服小金",
      message: "",
      text_content: "",
      ws: null,
      //企业名称
      companiesName: [],
    };
  },
  created() {
    this.getListComName();
  },
  methods: {
    join() {
      var myDate = new Date();
      var charTime = myDate.toLocaleTimeString();
      const wsuri = this.url;
      this.ws = new WebSocket(wsuri);
      const self = this;
      this.ws.onopen = function (event) {
        self.text_content = self.text_content + "已经打开连接!" + "\n";
        alert("连接成功！");
      };
      this.ws.onmessage = function (event) {
        self.text_content = event.data + "\n";
        // alert(evt.data + "ss");
        var msg_board = document.getElementsByClassName("msg_board")[0];
        //获取聊天内容
        var received_msg = event.data;
        //获取聊天用户
        var old_msg = msg_board.innerHTML + "<small>" + charTime + "</small>" + "<br>";
        msg_board.innerHTML = old_msg + received_msg + "  " + "<br>";
        // 让滚动块往下移动
        msg_board.scrollTop = msg_board.scrollTop + 80;
      };
      this.ws.onclose = function (event) {
        self.text_content = self.text_content + "已经关闭连接!" + "\n";
      };
    },
    exit() {
      if (this.ws) {
        this.ws.close();
        this.ws = null;
      }
    },
    send() {
      if (this.ws) {
        this.ws.send(this.message);
        this.message = "";
      } else {
        alert("未连接到服务器");
      }
    },
    // 查询已实名企业列表
    getListComName(){
      getAptitudeCompanyName().then(response => {
        this.companiesName = response.data;
      });
    },
    chartJoin(houseNo) {
      var myDate = new Date();
      var charTime = myDate.toLocaleTimeString();
      const churi = "ws://127.0.0.1:9090/webSocket/chat/"+houseNo+"/客服小金";
      this.ws = new WebSocket(churi);
      const self = this;
      this.ws.onopen = function (event) {
        self.text_content = self.text_content + "已经打开连接!" + "\n";
        alert("连接成功！");
      };
      this.ws.onmessage = function (event) {
        self.text_content = event.data + "\n";
        // alert(evt.data + "ss");
        var msg_board = document.getElementsByClassName("msg_board")[0];
        //获取聊天内容
        var received_msg = event.data;
        //获取聊天用户
        var old_msg = msg_board.innerHTML + "<small>" + charTime + "</small>" + "<br>";
        msg_board.innerHTML = old_msg + received_msg + "  " + "<br>";
        // 让滚动块往下移动
        msg_board.scrollTop = msg_board.scrollTop + 80;
      };
      this.ws.onclose = function (event) {
        self.text_content = self.text_content + "已经关闭连接!" + "\n";
      };
    },
    chartClick(e){
      this.chartJoin(e);
    },
  },
};
</script>
