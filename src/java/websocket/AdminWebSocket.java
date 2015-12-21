/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import model.Device;
import com.google.gson.*;
import requestOperations.RequestOperation;
import requestOperations.RequestOperationsSerializer;

/**
 *
 * @author maciej
 */
@ApplicationScoped
@ServerEndpoint("/admin")
public class AdminWebSocket {
    private static AdminWebSocket instance = null;
    public static AdminWebSocket getInstance() {
        if(instance == null) {
            instance = new AdminWebSocket();
        }
        return instance;
    }
    
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
    
    public void deviceRegistered(Device device) {
        Logger.getLogger(DeviceWebSocket.class.getName()).log(Level.SEVERE, "device registered", "as");
    }
    
    public void deviceUnregistered(Device device) {
        Logger.getLogger(DeviceWebSocket.class.getName()).log(Level.SEVERE, "device unregistered", "asd");
    }
    
    public void deviceSentData(Device device) {
        Logger.getLogger(DeviceWebSocket.class.getName()).log(Level.SEVERE, "device sent data", "asd");
    }
    
    

}
