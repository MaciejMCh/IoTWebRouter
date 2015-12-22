package controllers;

import java.util.Objects;
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayWebSocketMedium)) return false;
        final PlayWebSocketMedium that = (PlayWebSocketMedium) o;
        return Objects.equals(this.webSocketOut, that.webSocketOut);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(webSocketOut);
    }
    
}