/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Message {
    protected String dataType;
    protected String value;
    
    public String getDataType() {
        return this.dataType;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public Message(String dataType, String value) {
        this.dataType = dataType;
        this.value = value;
    }
}
