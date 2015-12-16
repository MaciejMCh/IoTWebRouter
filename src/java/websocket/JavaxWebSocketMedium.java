/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;
import model.Medium;

/**
 *
 * @author maciej
 */
public class JavaxWebSocketMedium implements Medium {

    protected Session session;
    
    public JavaxWebSocketMedium(Session session) {
        super();
        this.session = session;
    }
    
    @Override
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getLogger(JavaxWebSocketMedium.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
