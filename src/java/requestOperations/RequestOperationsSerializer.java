/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import com.google.gson.*;
import model.Medium;
import model.ModelSerializer;

/**
 *
 * @author maciej
 */
public abstract class RequestOperationsSerializer {
    
    protected abstract HashMap<String, Class> operationClassMap();
//            = new HashMap<String, Class>();
//    {{
//        put("register", RegisterOperation.class);
//        put("data", DataOperation.class);
//        put("log", LogOperation.class);
//        put("connect", ConnectOperation.class);
//        put("index_devices", IndexDevicesOperation.class);
//        put("index_connections", IndexConnectionsOperation.class);
//        put("connect_interfaces", ConnectInterfacesOperation.class);
//    }};
    
    public RequestOperation serializeOperation(JsonObject json, Medium medium) {
        String action = json.get("action").getAsString();
        Class operationClass = operationClassMap().get(action);
        if (operationClass != null) {
            try {
                RequestOperation operation = (RequestOperation)ModelSerializer.model(operationClass, json);
                operation.medium = medium;
                return operation;
            } catch (Exception e) {
                return new ErrorOperation(e.toString(), medium);
            }
            
        } else {
            return new ErrorOperation("Can't perform operation for action '" + action + "'.", medium);
        }
    }
    
}
