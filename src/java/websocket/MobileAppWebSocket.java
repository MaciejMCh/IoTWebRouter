/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.faces.bean.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import notificationCenter.NotificationCenter;
import requestOperations.MobileRequestOperationsSerializer;
import requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
@ApplicationScoped
@ServerEndpoint("/mobile")
public class MobileAppWebSocket {
    
    @OnOpen
    public void open(Session session) {
        NotificationCenter.getInstance().addMedium(new JavaxWebSocketMedium(session));
    }

    @OnClose
    public void close(Session session) {
        NotificationCenter.getInstance().removeMedium(new JavaxWebSocketMedium(session));
    }

    @OnError
    public void onError(Throwable error) {
        
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        JsonObject json = new JsonParser().parse(message).getAsJsonObject();
        RequestOperation operation;
        operation = new MobileRequestOperationsSerializer().serializeOperation(json, new JavaxWebSocketMedium(session));
        operation.performOperation();
    }
}
