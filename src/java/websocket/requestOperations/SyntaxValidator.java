/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author maciej
 */
public class SyntaxValidator {
    public static String validateSyntax(JSONObject json, JSONObject syntax) {
        
        String error = validateKeysMatching(syntax, json);
        
        
        
        return error;
    }
    
    
    protected static String validateKeysMatching(JSONObject syntax, JSONObject json) {
        String missingKeys = missingKeys(syntax, json);
        if (missingKeys != null) {
            return missingKeys;
        }
        for (String key : syntax.keySet()) {
            Object childSyntax = syntax.get(key);
            Object childJson = json.get(key);
            
            if (!childSyntax.getClass().equals(childJson.getClass())) {
                return key + " type is " + childJson.getClass().toString() + ", but expected type is " + childSyntax.getClass().toString();
            }
            
            if (childSyntax.getClass().equals(JSONObject.class)) {
                String childrenError = validateKeysMatching((JSONObject)childSyntax, (JSONObject)childJson);
                if (childrenError != null) {
                    return key + "." + childrenError;
                }
            }
        }
        return null;
    }
    
    protected static String missingKeys(JSONObject syntax, JSONObject json) {
        ArrayList<String> missingKeys = new ArrayList<>();
        for (String key : syntax.keySet()) {
            if (!json.keySet().contains(key)) {
                missingKeys.add(key);
            }
        }
        
        if (missingKeys.isEmpty()) {
            return null;
        }
        
        return missingKeys.toString();
    }
}
