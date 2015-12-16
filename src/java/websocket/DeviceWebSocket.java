/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import java.util.HashMap;
import javax.faces.bean.ApplicationScoped;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.*;
import model.Interactor;
import model.Medium;
import websocket.requestOperations.RequestOperation;
import websocket.requestOperations.RequestOperationsSerializer;

/**
 *
 * @author maciej
 */
@ApplicationScoped
@ServerEndpoint("/actions")
public class DeviceWebSocket {
    protected final HashMap<Session, Medium> mediumSessionMap = new HashMap<>();
    
    @OnOpen
    public void open(Session session) {
        
    }

    @OnClose
    public void close(Session session) {
        if (this.mediumSessionMap.get(session) == null) {
            return;
        }
        Interactor.getInstance().mediumClosed(this.mediumSessionMap.get(session));
    }

    @OnError
    public void onError(Throwable error) {
        
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        JsonObject json = new JsonParser().parse(message).getAsJsonObject();
        RequestOperation operation;
        operation = RequestOperationsSerializer.serializeOperation(json, new JavaxWebSocketMedium(session));
        operation.performOperation();
    }
}
