/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import com.google.gson.*;
import model.Medium;
import requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public class InterpretedLogOperation extends RequestOperation {

    protected ArrayList<String> options;
    protected ArrayList arguments;
    
//    public InterpretedLogOperation(JsonObject params, Medium medium) {
//        super(params, medium);
//        
//        if (params.entrySet().isEmpty()) {
//            // in case of reflect methods initialization
//            return;
//        }
//        
//        JsonArray argumentsJSONArray = params.get("arguments").getAsJsonArray();
//        this.arguments = new ArrayList();
//        for (int i=0; i<= argumentsJSONArray.size() - 1; i++) {
//            this.arguments.add(argumentsJSONArray.get(i).getAsString());
//        }
//        
//        JsonArray optionsJSONArray = params.get("options").getAsJsonArray();
//        this.options = new ArrayList<>();
//        for (int i=0; i<= optionsJSONArray.size() - 1; i++) {
//            this.options.add(optionsJSONArray.get(i).getAsString());
//        }
//        
//        this.mapArgumentsToProperties();
//        this.mapOptionsToProperties();
//        
//        String syntaxError = this.validateSyntax(params);
//        if (syntaxError != null) {
//            this.error(syntaxError);
//        }
//    }
    
    
    
    public String syntaxString() {
        String result = "";
        for (Argument argument : this.arguments()) {
            result += "'" + argument.getPropertyName() + "' ";
        }
        
        if (!this.options().isEmpty()) {
            result += "[";
            for (Option option : this.options()) {
                result += "-" + option.getRepresentation() + " ";
            }
            result += "]";
        }
        
        return result;
    }
    
    public String description() {
        return "";
    }
    
    protected ArrayList<Argument> arguments() {
        return new ArrayList<>();
    }
    
    protected ArrayList<Option> options() {
        return new ArrayList<>();
    }
    
    protected void log(String logMessage) {
        this.medium.sendMessage(logMessage);
    }
    
    private void mapArgumentsToProperties() {
        int i = 0;
        for (Argument argument : this.arguments()) {
            String propertyName = argument.getPropertyName();
            if (this.arguments.size() > i) {
                reflectiveSet(this, propertyName, this.arguments.get(i));
            }
            i++;
        }
    }
    
    private void mapOptionsToProperties() {
        int i = 0;
        for (Option option : this.options()) {
            String propertyName = option.getPropertyName();
            if (this.options.contains(option.getRepresentation())) {
                reflectiveSet(this, propertyName, true);
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

    @Override
    public void performOperation() {
        
    }
    
}

class Argument {
    protected String propertyName;
    protected String description;
    protected boolean isRequired;
    public String getDescription() {
        return description;
    }
    public String getPropertyName() {
        return propertyName;
    }    
    public boolean getIsRequired() {
        return this.isRequired;
    }    
    public Argument(String propertyName, String description, boolean isRequired) {
        this.propertyName = propertyName;
        this.description = description;
        this.isRequired = isRequired;
    }
}
    
class Option {
    protected String name;
    protected String description;
    protected String propertyName;
    protected String representation;
    public String getName() {
        return name;
    }
    public String getPropertyName() {
        return propertyName;
    }
    public String getRepresentation() {
        return representation;
    }
    public String getDescription() {
        return description;
    }
    public Option(String name, String description, String propertyName, String representation) {
        this.name = name;
        this.description = description;
        this.propertyName = propertyName;
        this.representation = representation;
    }        
}
