
$(function(){
    $.notifySetup({sound:'jquery.notify.wav'});

    var client = Stomp.client("ws://localhost:15674/ws");
    var onConnect = function(){
        client.subscribe("/exchange/direct-exchange-A/da",function(message){
            $("<p>"+message.body+"</p>").notify({stay:10000});
        });
    };
    var onError = function(message){
        $("<p>"+message.body+"</p>").notify({stay:10000});
    };
    client.connect("yhao","290010",onConnect,onError);
    client.heartbeat.incoming = 5000;
    client.heartbeat.outgoing = 5000;
});