var stompClient = null;

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    $('#response').html();
}

function connect() {
    // websocket的连接地址，此值等于WebSocketMessageBrokerConfigurer中registry.addEndpoint("/websocket-simple").withSockJS()配置的地址
    var socket = new SockJS('/websocket-simple');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        // 客户端订阅消息的目的地址：此值BroadcastCtl中被@SendTo("/topic/getResponse")注解的里配置的值
        stompClient.subscribe('/topic/getResponse', function(respnose){
            showResponse(JSON.parse(respnose.body).responseMessage);
        });
    });
}


function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    var name = $('#name').val();
    // 客户端消息发送的目的：服务端使用BroadcastCtl中@MessageMapping("/receive")注解的方法来处理发送过来的消息
    stompClient.send("/receive", {}, JSON.stringify({ 'name': name }));
}

function showResponse(message) {
    var response = $("#response");
    response.html(message + "\r\n" + response.html());
}

$(function(){
    disconnect();
});