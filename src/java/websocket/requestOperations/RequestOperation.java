/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import javax.websocket.Session;
import com.google.gson.*;

/**
 *
 * @author maciej
 */
public class RequestOperation {
    
    protected Session session;
    protected Error error;

    public Error getError() {
        return error;
    }
    
    public RequestOperation(JsonObject params, Session session) {
        this.session = session;
        
        String sytnaxError = SyntaxValidator.validateSyntax(this.getSyntax(), params);
        if (sytnaxError != null) {
            this.error("Syntax error. " + sytnaxError);
            return;
        }
    }
    
    public void performOperation() {
        
    }
    
    protected void error(String errorMessage) {
        this.error = new Error(errorMessage);
    }
    
    protected JsonObject getSyntax() {
        return new JsonObject();
    }
}
