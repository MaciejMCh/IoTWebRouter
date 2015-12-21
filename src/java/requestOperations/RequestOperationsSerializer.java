/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import websocket.requestOperations.Log.LogOperation;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import com.google.gson.*;
import conversation.ConnectInterfacesOperation;
import conversation.IndexConnectionsOperation;
import conversation.IndexDevicesOperation;
import model.Medium;

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
        put("index_devices", IndexDevicesOperation.class);
        put("index_connections", IndexConnectionsOperation.class);
        put("connect_interfaces", ConnectInterfacesOperation.class);
    }};
    
    public static RequestOperation serializeOperation(JsonObject json, Medium medium) {
        String action = json.get("action").getAsString();
        Class operationClass = operationClassMap.get(action);
        if (operationClass != null) {
            try {
                Constructor constructor = operationClass.getDeclaredConstructor(JsonObject.class, Medium.class);
                RequestOperation operation = (RequestOperation)constructor.newInstance(json, medium);
                return operation;
            } catch (Exception e) {
                return ErrorOperation.internalServerErrorOperation(medium);
            }
            
        } else {
            return new ErrorOperation("Can't perform operation for action '" + action + "'.", medium);
        }
    }
    
}
