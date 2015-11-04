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
        put("connection", ConnectionsLogOperation.class);
        put("help", HelpLogOperation.class);
    }};

    public static HashMap<String, Class> getOperationClassMap() {
        return operationClassMap;
    }
    
    public static RequestOperation serializeOperation(JsonObject json, Session session) {
        if (json.has("query")) {
            return serializeOperation(parseQuery(json.get("query").getAsString()), session);
        }
        
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
    
    protected static JsonObject parseQuery(String query) {
        JsonObject parsedQuery = new JsonObject();
        JsonArray options = new JsonArray();
        JsonArray arguments = new JsonArray();
        
        String[] syntax = query.split(" ");
        for (String element : syntax) {
            // ignore empty string
            if (element.equals("")) {
                continue;
            }
            
            // first element is action
            if (syntax[0] == element) {
                parsedQuery.add("action", new JsonPrimitive(element));
                continue;
            } 
            
            // option
            if (element.startsWith("-")) {
                options.add(new JsonPrimitive(element.replace("-", "")));
                continue;
            }
            
            // argument
            arguments.add(new JsonPrimitive(element));
        }
        
        parsedQuery.add("options", options);
        parsedQuery.add("arguments", arguments);
        
        return parsedQuery;
    }
    
}
