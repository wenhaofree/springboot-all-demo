## 使用
- 依赖
```
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```
- 注解：
    - @Configuration
        - ServerEndpointExporter
    - @ServerEndpoint("/imserver/{userId}")
        - @OnOpen、 @OnClose、@OnMessage、@OnError
- 配置文件-可不用配置
```
server:
  port: 9999
  servlet:
    context-path: /demo
spring:
  http:
    encoding:
      force: true
      charset: UTF-8
  application:
    name: spring-cloud-study-websocket-reids
  freemarker:
    request-context-attribute: request
    #prefix: /templates/
    suffix: .html
    content-type: text/html
    enabled: true
    cache: false
    charset: UTF-8
    allow-request-override: false
    expose-request-attributes: true
    expose-session-attributes: true
    expose-spring-macro-helpers: true
```
- 页面配置编写：
```
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>websocket通讯</title>
</head>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script>
    var socket;
    function openSocket() {
        if(typeof(WebSocket) == "undefined") {
            console.log("您的浏览器不支持WebSocket");
        }else{
            console.log("您的浏览器支持WebSocket");
            //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
            //等同于socket = new WebSocket("ws://localhost:8888/xxxx/im/25");
            //var socketUrl="${request.contextPath}/im/"+$("#userId").val();
            var socketUrl="http://localhost:9999/demo/imserver/"+$("#userId").val();
            socketUrl=socketUrl.replace("https","ws").replace("http","ws");
            console.log(socketUrl);
            if(socket!=null){
                socket.close();
                socket=null;
                console.log("socket不为空");
            }
            socket = new WebSocket(socketUrl);
            //打开事件
            socket.onopen = function() {
                console.log("websocket已打开");
                socket.send("这是来自客户端的消息：" + location.href + new Date());
            };
            //获得消息事件
            socket.onmessage = function(msg) {
                console.log("消息时间："+msg.data);
                //发现消息进入    开始处理前端触发逻辑
            };
            //关闭事件
            socket.onclose = function() {
                console.log("websocket已关闭");
            };
            //发生了错误事件
            socket.onerror = function() {
                console.log("websocket发生了错误");
            }
        }
    }
    function sendMessage() {
        if(typeof(WebSocket) == "undefined") {
            console.log("您的浏览器不支持WebSocket");
        }else {
            console.log("您的浏览器支持WebSocket");
            console.log('{"toUserId":"'+$("#toUserId").val()+'","contentText":"'+$("#contentText").val()+'"}');
            socket.send('{"toUserId":"'+$("#toUserId").val()+'","contentText":"'+$("#contentText").val()+'"}');
        }
    }
</script>
<body>
<p>【userId】：<div><input id="userId" name="userId" type="text" value="10"></div>
<p>【toUserId】：<div><input id="toUserId" name="toUserId" type="text" value="20"></div>
<p>【toUserId】：<div><input id="contentText" name="contentText" type="text" value="hello websocket"></div>
<p>【操作】：<div><a onclick="openSocket()"><button>开启socket</button></a></div>
<p>【操作】：<div><a onclick="sendMessage()"><button>发送消息</button></a></div>
</body>

</html>

```

## 逻辑流程图：
- 前端触发
- 建立WebSocket连接
- 刷新都会关闭连接
- 页面的socket.send 会被服务端的@OnMessage捕捉处理
- 服务端的“this.session.getBasicRemote().sendText(message);” 会发送给页面前端被“socket.onmessage”捕捉输出。
