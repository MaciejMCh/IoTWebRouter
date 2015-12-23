/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import sun.security.action.GetBooleanAction;

/**
 *
 * @author maciej
 */
public class ModelTestClass implements SerializableModel {

    public String stringProperty;
    public int intProperty;
    public long longProperty;
    public float floatProperty;
    public double doubleProperty;
    public boolean booleanProperty;
    
    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        return new HashMap<String, String>() {
            {
                put("stringProperty", "string_property");
                put("intProperty", "int_property");
                put("longProperty", "long_property");
                put("floatProperty", "float_property");
                put("doubleProperty", "double_property");
                put("booleanProperty", "boolean_property");
            }
        };
    }

    public String getStringProperty() {
        return stringProperty;
    }
    
    public int getIntProperty() {
        return intProperty;
    }

    public long getLongProperty() {
        return longProperty;
    }

    public float getFloatProperty() {
        return floatProperty;
    }

    public double getDoubleProperty() {
        return doubleProperty;
    }
    
    public boolean getBooleanProperty() {
        return booleanProperty;
    }
    
}
