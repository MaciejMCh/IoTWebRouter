/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import javax.websocket.Session;
import org.json.JSONObject;
import websocket.AdminWebSocket;

/**
 *
 * @author maciej
 */
public class DataOperation extends RequestOperation {

    public DataOperation(JSONObject params, Session session) {
        super(params, session);
        
    }
    
    @Override
    public void performOperation() {
        AdminWebSocket.getInstance().deviceSentData(null);
    }
    
}
