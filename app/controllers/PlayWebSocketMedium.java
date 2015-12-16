package controllers;

import model.Medium;
import play.mvc.WebSocket;

class PlayWebSocketMedium implements Medium {
    
    protected WebSocket.Out<String> webSocketOut;
    
    public PlayWebSocketMedium(WebSocket.Out<String> webSocketOut) {
        super();
        this.webSocketOut = webSocketOut;
    }
    
    @Override
    public void sendMessage(String message) {
        this.webSocketOut.write(message);
    }
    
}