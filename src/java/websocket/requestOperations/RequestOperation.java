/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import javax.websocket.Session;
import org.json.JSONObject;

/**
 *
 * @author maciej
 */
public class RequestOperation {
    
    protected Session session;
    
    public RequestOperation(JSONObject params, Session session) {
        this.session = session;
    }
    
    public void performOperation() {
        
    }
}
