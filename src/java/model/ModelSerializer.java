package model;

import com.google.gson.JsonObject;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelSerializer {
    public static SerializableModel model(Class clazz, JsonObject json) {
        
        try {
            SerializableModel model = (SerializableModel) clazz.newInstance();
            
            for (String propertyName : model.JSONKeyPathsByPropertyKey().keySet()) {
                String jsonName = model.JSONKeyPathsByPropertyKey().get(propertyName);
                if (json.has(jsonName)) {
                    reflectiveSet(model, propertyName, json.get(jsonName).getAsString());
                }
            }
            
            return model;
        } catch(Exception e) {
            
        }
        
        return null;
    }
    
    private static boolean reflectiveSet(Object object, String fieldName, String fieldValue) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                
                if (field.getGenericType() == int.class) {
                    field.set(object, Integer.parseInt(fieldValue));
                    return true;
                }
                
                if (field.getGenericType() == String.class) {
                    field.set(object, fieldValue);
                    return true;
                }
                
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