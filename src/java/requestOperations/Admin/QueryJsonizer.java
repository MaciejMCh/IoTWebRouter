/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import com.google.gson.*;
import java.util.ArrayList;
import java.util.HashMap;

public class QueryJsonizer {
    
    public static JsonObject jsonizeQuery(String query, HashMap<String, Class> operationClassMap) {
        
        String action = "unknown";
        ArrayList<String> options = new ArrayList<>();
        ArrayList<String> arguments = new ArrayList<>();
        
        String[] syntax = query.split(" ");
        for (String element : syntax) {
            // ignore empty string
            if (element.equals("")) {
                continue;
            }
            
            // first element is action
            if (syntax[0] == element) {
                action = element;
                continue;
            } 
            
            // option
            if (element.startsWith("-")) {
                options.add(element.replace("-", ""));
                continue;
            }
            
            // argument
            arguments.add(element);
        }
        
        
        try {
            if (operationClassMap.get(action) == null) {
                return null;
            }
            InterpretedOperation operationObject = (InterpretedOperation) operationClassMap.get(action).newInstance();
            JsonObject json = new JsonObject();
            json.addProperty("action", action);
            for (int i=0; i<=arguments.size() - 1; i++) {
                json.addProperty(operationObject.arguments().get(i).getArgumentName(), arguments.get(i));
            }
            
            for (Option option : operationObject.options()) {
                String optionName = option.getInvocation();
                json.addProperty(option.getName(), options.contains(option.getInvocation()));
            }
            return json;
        } catch(Exception e) {
            return null;
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
