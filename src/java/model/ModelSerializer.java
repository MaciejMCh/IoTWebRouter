package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ModelSerializer {
    public static SerializableModel model(Class clazz, JsonObject json) throws SerializationErrorException {
        try {
            SerializableModel model = (SerializableModel) clazz.newInstance();
            
            for (String propertyExpression : model.JSONKeyPathsByPropertyKey().keySet()) {
                String jsonName = model.JSONKeyPathsByPropertyKey().get(propertyExpression);
                String propertyName = propertyExpression.replace("!", "");
                if (json.has(jsonName)) {
                    reflectiveSet(model, propertyName, json.get(jsonName));
                } else {
                    if (propertyExpression.contains("!")) {
                        throw new SerializationErrorException(jsonName + " field is missing.");
                    }
                }
            }
            
            try {
                Method init = clazz.getDeclaredMethod("init");
                init.setAccessible(true);
                init.invoke(model);
            } catch(Exception e) {
                
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
                
                if (field.getGenericType().getTypeName().contains("ArrayList")) {
                    String genericClassString = field.getGenericType().getTypeName();
                    genericClassString = genericClassString.substring(genericClassString.indexOf("<") + 1, genericClassString.indexOf(">"));
                    Class genericClazz = Class.forName(genericClassString);
                    ArrayList<Object> models = new ArrayList<>();
                    JsonArray jsonArray = fieldValue.getAsJsonArray();
                    for (JsonElement jsonElement : jsonArray) {
                        Object model = ModelSerializer.model(genericClazz, jsonElement.getAsJsonObject());
                        models.add(model);
                    }
                    field.set(object, models);
                    return true;
                }
                
                if (((Class)field.getGenericType()).isEnum()) {
                    try {
                        Method method = clazz.getMethod(fieldName+"EnumMap");
                        HashMap<String, Object> enumMap = (HashMap<String, Object>) method.invoke(object);
                        field.set(object, enumMap.get(fieldValue.getAsString()));
                        return true;
                    } catch(Exception e) {
                        System.out.println(fieldName+"EnumMap method not found");
                        return false;
                    }
                }
                
                if (Arrays.asList(((Class)field.getGenericType()).getInterfaces()).contains(SerializableModel.class)) {
                    Object model = model((Class) field.getGenericType(), fieldValue.getAsJsonObject());
                    field.set(object, model);
                    return true;
                }
                
                throw new SerializationErrorException(fieldName + " is unprocessable.");
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
    }
}