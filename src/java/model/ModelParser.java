///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package model;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// *
// * @author maciej
// */
//public class ModelParser {
//    public static JsonObject json(SerializableModel model) {
//        JsonObject json = new JsonObject();
//        
//        
//        for (String propertyName : model.JSONKeyPathsByPropertyKey().keySet()) {
//            String fieldName = model.JSONKeyPathsByPropertyKey().get(propertyName);
//            propertyName = propertyName.replace("!", "");
//            
//            Class<?> clazz = model.getClass();
//            if(clazz != null) {
//                try {
//                    Field field = clazz.getDeclaredField(propertyName);
//                    field.setAccessible(true);
//                    Object fieldContent = field.get(model);
//                    
//                    json.addProperty(fieldName, jsonFromObject(fieldContent).toString());
//                } catch(Exception e) {
//                    System.out.println(e);
//                }
//            }
//            
//        }
//        
//        
//        
//        
//        
//        return json;
//    };
//    
//    
//    
//    private static JsonObject jsonFromObject(Object object) {
//        
//        if (object == null) {
//            return null;
//        }
//                    
////        if (object.getGenericType().getTypeName().contains("ArrayList")) {
////                        JsonArray jsonArray = new JsonArray();
////                        ArrayList<Object> list = (ArrayList<Object>) fieldContent;
////                        for (Object element : list) {
////                            jsonArray.add(ModelParser.json(element));
////                        }
////                        continue;
////                    }
//                    
//        if (SerializableModel.class.isAssignableFrom((object.getClass()))) {
//            JsonObject childJson = ModelParser.json((SerializableModel)object);
//            return childJson;
//        }
//                    
//        if (object.getClass().isEnum()) {
//            try {
//                Class clazz = object.getClass();
//                Method method = clazz.getMethod(propertyName+"EnumMap");
//                HashMap<String, Object> enumMap = (HashMap<String, Object>) method.invoke(model);
//                for (Map.Entry<String, Object> entry :enumMap.entrySet()) {
//                    if (entry.getValue().toString().equals(fieldContent.toString())) {
//                        json.addProperty(fieldName, entry.getKey());
//                        break;
//                    }
//                            }
//                        } catch(Exception e) {
//                            System.out.println(fieldName+"EnumMap method not found");
//                        }
//                        continue;
//                    }
//                    
//        
//        return JsonObject().js
//        json.addProperty(fieldName, fieldContent.toString());
//    }
//    
//    
//}
