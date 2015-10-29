/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
//import javax.enterprise.context.ApplicationScoped;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.*;
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
        
    }

    @OnError
    public void onError(Throwable error) {
        
    }
    
//    @OnMessage
//public void onMessage(ByteBuffer byteBuffer) {
//    byte[] bytes = byteBuffer.array();
//    String string;
//        try {
//            string = new String(bytes, "UTF-8");
//            Logger.getLogger(DeviceSessionHandler.class.getName()).log(Level.SEVERE, string, string);
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(DeviceWebSocket.class.getName()).log(Level.SEVERE, null, ex);
//        }
    
    
//  for (Session session : sessions) {
//    try {
//      session.getBasicRemote().sendBinary(byteBuffer);
//    } catch (IOException ex) {
//      Logger.getLogger(BinaryWebSocketServer.class.getName()).log(Level.SEVERE, null, ex);
//    }
//  }
//}

    @OnMessage
    public void handleMessage(String message, Session session) {
        Logger.getLogger(DeviceSessionHandler.class.getName()).log(Level.SEVERE, message, message);
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            //Logger.getLogger(DeviceSessionHandler.class.getName()).log(Level.SEVERE, "sending error", ex);
        }
    }
}
