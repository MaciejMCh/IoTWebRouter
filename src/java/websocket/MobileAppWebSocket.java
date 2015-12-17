/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import conversation.Conversable;
import java.util.HashMap;
import javax.faces.bean.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author maciej
 */
@ApplicationScoped
@ServerEndpoint("/mobile")
public class MobileAppWebSocket {
    
    protected HashMap<Session, Conversable> sessionConversableMap = new HashMap();
    
    @OnOpen
    public void open(Session session) {
        
    }

    @OnClose
    public void close(Session session) {
        
    }

    @OnError
    public void onError(Throwable error) {
        
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        
    }
}
