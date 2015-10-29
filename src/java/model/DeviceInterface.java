/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static model.InterfaceDirection.Input;
import static model.InterfaceDirection.Output;
import org.json.JSONObject;

/**
 *
 * @author maciej
 */

enum InterfaceDirection {
    Input, Output
}

public class DeviceInterface {
    public String id;
    public String dataType;
    public InterfaceDirection interfaceDirection;
    
    public DeviceInterface(JSONObject json) {
        this.id = json.getString("id");
        this.dataType = json.getString("dataType");
        String interfaceDirection = json.getString("direction");
        if (interfaceDirection == "input") {
            this.interfaceDirection = Input;
        } else {
            this.interfaceDirection = Output;
        }
    }
}
