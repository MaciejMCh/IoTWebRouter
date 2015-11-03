/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;
import org.json.JSONArray;
import com.google.gson.*;
import websocket.requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public class InterpretedLogOperation extends RequestOperation {

    protected ArrayList<String> options;
    protected ArrayList arguments;
    
    public InterpretedLogOperation(JsonObject params, Session session) {
        super(params, session);
        
        JsonArray argumentsJSONArray = params.get("arguments").getAsJsonArray();
        this.arguments = new ArrayList();
        for (int i=0; i<= argumentsJSONArray.size() - 1; i++) {
            this.arguments.add(argumentsJSONArray.get(i).getAsString());
        }
        
        JsonArray optionsJSONArray = params.get("options").getAsJsonArray();
        this.options = new ArrayList<>();
        for (int i=0; i<= optionsJSONArray.size() - 1; i++) {
            this.options.add(optionsJSONArray.get(i).getAsString());
        }
        
        this.mapArgumentsToProperties();
    }
    
    protected ArrayList<String> propertyNames() {
        return new ArrayList<>();
    }
    
    protected void log(String logMessage) {
        try {
            this.session.getBasicRemote().sendText(logMessage);
        } catch (IOException ex) {
            Logger.getLogger(LogOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mapArgumentsToProperties() {
        int i = 0;
        for (String propertyName : this.propertyNames()) {
            if (this.arguments.size() > i) {
                reflectiveSet(this, propertyName, this.arguments.get(i));
            }
            i++;
        }
    }
    
    private static boolean reflectiveSet(Object object, String fieldName, Object fieldValue) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, fieldValue);
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
    }
}
