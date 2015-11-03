/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import javax.websocket.Session;
import com.google.gson.*;
import websocket.requestOperations.ErrorOperation;
import websocket.requestOperations.RequestOperation;

public class LogRequestInterpreter {
    protected static final HashMap<String, Class> operationClassMap = new HashMap<String, Class>() {{
        put("device", DeviceLogOperation.class);
    }};
    
    public static RequestOperation serializeOperation(JsonObject json, Session session) {
        String action = json.get("action").getAsString();
        Class operationClass = operationClassMap.get(action);
        if (operationClass != null) {
            try {
                Constructor constructor = operationClass.getDeclaredConstructor(JsonObject.class, Session.class);
                RequestOperation operation = (RequestOperation)constructor.newInstance(json, session);
                return operation;
            } catch (Exception e) {
                return ErrorOperation.internalServerErrorOperation(session);
            }
        } else {
            return new ErrorOperation("Unknown log action '" + action + "'.", session);
        }
    }
}
