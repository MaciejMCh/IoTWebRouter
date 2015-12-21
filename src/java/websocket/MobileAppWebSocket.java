/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import requestOperations.RequestOperation;
import requestOperations.RequestOperationsSerializer;

/**
 *
 * @author maciej
 */
@ApplicationScoped
@ServerEndpoint("/mobile")
public class MobileAppWebSocket {
    
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
        RequestOperation operation;
        JsonObject json = new JsonParser().parse(message).getAsJsonObject();
        operation = RequestOperationsSerializer.serializeOperation(json, new JavaxWebSocketMedium(session));
        
        if (operation.getError() == null) {
            operation.performOperation();
        } else {
            try {
                session.getBasicRemote().sendText(operation.getError().getErrorMessage());
            } catch (IOException ex) {
                Logger.getLogger(AdminWebSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
