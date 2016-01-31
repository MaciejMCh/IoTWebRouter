/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.io.IOException;
import java.util.Objects;
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

    @Override
    public void close() {
        try {
            this.session.close();
        } catch (IOException ex) {
            Logger.getLogger(JavaxWebSocketMedium.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JavaxWebSocketMedium)) return false;
        final JavaxWebSocketMedium that = (JavaxWebSocketMedium) o;
        return Objects.equals(this.session, that.session);
    }
    @Override
    public int hashCode() {
        return Objects.hash(session);
    }
    
}
