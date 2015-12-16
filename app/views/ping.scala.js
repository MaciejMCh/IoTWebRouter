$(function() {
    var WS = window['MozWebSocket'] ? MozWebSocket : WebSocket
    var dateSocket = new WS("@routes.Application.ping().webSocketURL(request)")

    var receiveEvent = function(event) {
        $("#ping").html("Last ping: "+event.data);
    }

    dateSocket.onmessage = receiveEvent
})
