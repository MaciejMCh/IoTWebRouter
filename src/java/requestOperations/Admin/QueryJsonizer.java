/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import com.google.gson.*;
import java.util.ArrayList;

public class QueryJsonizer {
    
    public static JsonObject jsonizeQuery(String query, Class interpretedOperationClass) {
        
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
            InterpretedOperation operationObject = (InterpretedOperation) interpretedOperationClass.newInstance();
            JsonObject json = new JsonObject();
            json.addProperty("action", action);
            for (int i=0; i<=arguments.size() - 1; i++) {
                json.addProperty(operationObject.argumentPropertyNames().get(i), arguments.get(i));
            }
            
            for (String optionKey : operationObject.optionByPropertyKey().keySet()) {
                String optionName = operationObject.optionByPropertyKey().get(optionKey);
                json.addProperty(optionName, options.contains(optionKey));
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
