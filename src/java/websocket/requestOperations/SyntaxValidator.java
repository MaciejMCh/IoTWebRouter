/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import com.google.gson.*;
import java.util.Map;

/**
 *
 * @author maciej
 */
public class SyntaxValidator {
    public static String validateSyntax(JsonElement syntax, JsonElement json) {
        // Json Object
        if (syntax.isJsonObject() && json.isJsonObject()) {
            JsonObject oJson = json.getAsJsonObject();
            JsonObject oSyntax = syntax.getAsJsonObject();
            // check keys matching
            for (Map.Entry<String, JsonElement> entry : oSyntax.entrySet()) {
                if (!oJson.has(entry.getKey())) {
                    return entry.getKey() + " entry is missing.";
                }
            }
        
            // check types matching
            for (Map.Entry<String, JsonElement> entry : oSyntax.entrySet()) {
                if (!oJson.get(entry.getKey()).getClass().equals(entry.getValue().getClass())) {
                    return entry.getKey() + " entry type is " + getStringOfClass(oJson.get(entry.getKey())) + ", but expected type is " + getStringOfClass(entry.getValue()) + ".";
                }
            }
            
            // check children errors
            for (Map.Entry<String, JsonElement> entry : oSyntax.entrySet()) {
                String childError = validateSyntax(entry.getValue(), oJson.get(entry.getKey()));
                if (childError != null) {
                    return entry.getKey() + "." + childError;
                }
            }
        }
        return null;
    }
    
    protected static String getStringOfClass(Object o) {
        return o.getClass().toString().substring(o.getClass().toString().lastIndexOf(".") + 1);
    }
    
}
