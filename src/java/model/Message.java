/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;

public class Message implements SerializableModel {
    protected String dataType;
    protected String value;
    
    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        return new HashMap<String, String>() {
            {
                put("dataType", "data_type");
                put("value", "value");
            }
        };
    }
    
    public String getDataType() {
        return this.dataType;
    }
    
    public Object getValue() {
        return this.value;
    }
    
}
