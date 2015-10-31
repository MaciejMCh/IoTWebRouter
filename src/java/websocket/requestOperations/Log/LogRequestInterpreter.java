/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;
import org.json.JSONObject;
import websocket.requestOperations.DataOperation;
import websocket.requestOperations.RegisterOperation;
import websocket.requestOperations.RequestOperation;
import websocket.requestOperations.RequestOperationsSerializer;

/**
 *
 * @author maciej
 */
public class LogRequestInterpreter {
    protected static final HashMap<String, Class> operationClassMap = new HashMap<String, Class>() {{
        put("device", DeviceLogOperation.class);
    }};
    
    public static RequestOperation serializeOperation(JSONObject json, Session session) throws InstantiationException, IllegalAccessException {
        HashMap map = operationClassMap;
        String action = json.getString("action");
        Class operationClass = operationClassMap.get(action);
        String s = "";
        if (operationClass != null) {
            try {
                Constructor constructor = operationClass.getDeclaredConstructor(JSONObject.class, Session.class);
                try {
                    RequestOperation operation = (RequestOperation)constructor.newInstance(json, session);
                    return operation;
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(RequestOperationsSerializer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(RequestOperationsSerializer.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(RequestOperationsSerializer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(RequestOperationsSerializer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    
}