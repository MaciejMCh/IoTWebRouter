/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import javax.websocket.Session;
import org.json.JSONObject;

/**
 *
 * @author maciej
 */
public class DeviceLogOperation extends LogOperation {

    public DeviceLogOperation(JSONObject params, Session session) {
        super(params, session);
    }
    
}
