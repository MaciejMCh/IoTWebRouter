/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;

/**
 *
 * @author maciej
 */
public class ModelTestClass implements SerializableModel {

    public String stringProperty;
    public int intProperty;
    public float floatProperty;
    
    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        return new HashMap<String, String>() {
            {
                put("stringProperty", "string_property");
                put("intProperty", "int_property");
                put("floatProperty", "float_property");
            }
        };
    }

    public int getIntProperty() {
        return intProperty;
    }
    
    public String getStringProperty() {
        return stringProperty;
    }

    public float getFloatProperty() {
        return floatProperty;
    }
    
    
    
}
