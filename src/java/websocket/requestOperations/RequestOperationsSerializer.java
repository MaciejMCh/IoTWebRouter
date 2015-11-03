/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import websocket.requestOperations.Log.LogOperation;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import javax.websocket.Session;
import org.json.JSONObject;

/**
 *
 * @author maciej
 */
public class RequestOperationsSerializer {
    
    protected static final HashMap<String, Class> operationClassMap = new HashMap<String, Class>() {{
        put("register", RegisterOperation.class);
        put("data", DataOperation.class);
        put("log", LogOperation.class);
        put("connect", ConnectOperation.class);
    }};
    
    public static RequestOperation serializeOperation(JSONObject json, Session session) {
        String action = json.getString("action");
        Class operationClass = operationClassMap.get(action);
        if (operationClass != null) {
            try {
                Constructor constructor = operationClass.getDeclaredConstructor(JSONObject.class, Session.class);
                RequestOperation operation = (RequestOperation)constructor.newInstance(json, session);
                return operation;
            } catch (Exception e) {
                return ErrorOperation.internalServerErrorOperation(session);
            }
            
        } else {
            return new ErrorOperation("Can't perform operation for action '" + action + "'.", session);
        }
    }
    
}
