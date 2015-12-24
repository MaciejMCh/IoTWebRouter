package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelSerializer {
    public static SerializableModel model(Class clazz, JsonObject json) {
        
        try {
            SerializableModel model = (SerializableModel) clazz.newInstance();
            
            for (String propertyName : model.JSONKeyPathsByPropertyKey().keySet()) {
                String jsonName = model.JSONKeyPathsByPropertyKey().get(propertyName);
                if (json.has(jsonName)) {
                    reflectiveSet(model, propertyName, json.get(jsonName));
                }
            }
            
            return model;
        } catch(Exception e) {
            System.out.println(e);
        }
        
        return null;
    }
    
    private static boolean reflectiveSet(Object object, String fieldName, JsonElement fieldValue) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                
                if (field.getGenericType() == String.class) {
                    field.set(object, fieldValue.getAsString());
                    return true;
                }
                
                if (field.getGenericType() == int.class) {
                    field.set(object, fieldValue.getAsInt());
                    return true;
                }
                
                if (field.getGenericType() == long.class) {
                    field.set(object, fieldValue.getAsLong());
                    return true;
                }
                
                if (field.getGenericType() == float.class) {
                    field.set(object, fieldValue.getAsFloat());
                    return true;
                }
                
                if (field.getGenericType() == double.class) {
                    field.set(object, fieldValue.getAsDouble());
                    return true;
                }
                
                if (field.getGenericType() == boolean.class) {
                    field.set(object, fieldValue.getAsBoolean());
                    return true;
                }
                
                if (Arrays.asList(field.getGenericType().getClass().getInterfaces()).contains(Serializable.class)) {
                    Object model = model((Class) field.getGenericType(), fieldValue.getAsJsonObject());
                    field.set(object, model);
                }
                        
                return false;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
    }
}