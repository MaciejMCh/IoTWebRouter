/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import javax.faces.bean.ApplicationScoped;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.*;
import model.Interactor;
import requestOperations.DeviceRequestOperationsSerializer;
import requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
@ApplicationScoped
@ServerEndpoint("/actions")
public class DeviceWebSocket {
    
    @OnOpen
    public void open(Session session) {
        
    }

    @OnClose
    public void close(Session session) {
        Interactor.getInstance().mediumClosed(new JavaxWebSocketMedium(session));
    }

    @OnError
    public void onError(Throwable error) {
        
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        JsonObject json = new JsonParser().parse(message).getAsJsonObject();
        RequestOperation operation;
        operation = new DeviceRequestOperationsSerializer().serializeOperation(json, new JavaxWebSocketMedium(session));
        operation.performOperation();
    }
}
