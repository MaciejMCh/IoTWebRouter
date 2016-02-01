/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashMap;

public class Message implements SerializableModel {
    protected String dataType;
    protected JsonElement value;
    
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
    
    public JsonElement getValue() {
        return this.value;
    }
    
}
