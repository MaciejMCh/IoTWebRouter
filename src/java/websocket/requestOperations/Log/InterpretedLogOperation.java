/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.websocket.Session;
import org.json.JSONObject;
import websocket.requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public class InterpretedLogOperation extends RequestOperation {

    public ArrayList<String> options;
    public ArrayList arguments;
    
    public InterpretedLogOperation(JSONObject params, Session session) {
        super(params, session);
        ArrayList<String> list = this.propertyNames();
        list = this.propertyNames();
        list = this.propertyNames();
        
        this.mapArgumentsToProperties();
//        this.arguments = params.getJSONArray("arguments").;
//        this.options = patams.get
    }
    
    public void mapArgumentsToProperties() {
//        this.propertyNames()[0]
          reftSet(this, this.propertyNames().get(0), "duu");
//        for (String propertyName : this.propertyNames()) {
//            set(this, propertyName, "duuuuu");
//        }
    }
    
    public ArrayList<String> propertyNames() {
        return new ArrayList<>();
    }
    
    
    
    public static boolean reftSet(Object object, String fieldName, Object fieldValue) {
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
