/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;
import org.json.JSONObject;

/**
 *
 * @author maciej
 */
public class ErrorOperation extends RequestOperation {

    public String errorMessage;
    
    public ErrorOperation(JSONObject params, Session session) {
        super(params, session);
    }
    
    public ErrorOperation(String errorMessage, Session session) {
        super(null, session);
        this.errorMessage = errorMessage;
    }
    
    public static ErrorOperation internalServerErrorOperation(Session session) {
        return new ErrorOperation("Internal server error.", session);
    }

    @Override
    public void performOperation() {
        try {
            this.session.getBasicRemote().sendText(this.errorMessage);
        } catch (IOException ex) {
            Logger.getLogger(ErrorOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
