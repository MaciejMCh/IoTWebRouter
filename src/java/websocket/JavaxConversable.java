/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import conversation.Conversable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

/**
 *
 * @author maciej
 */
public class JavaxConversable extends Conversable {

    protected Session session;
    
    public JavaxConversable(Session session) {
        super();
        this.session = session;
        session.addMessageHandler(new MessageHandler() {
            public void onMessage(String message) {
                JavaxConversable.this.receivedMessage(message);
            }    
        });
    }
    
    @Override
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getLogger(JavaxConversable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
