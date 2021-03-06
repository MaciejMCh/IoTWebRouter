/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.*;
import java.util.HashMap;

/**
 *
 * @author maciej
 */
public class Signal implements SerializableModel {
    protected Message message;
    protected String sourceInterfaceID;
    
    public DeviceInterface sourceInterface;
    public DeviceInterface destinationInterface;
    
    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        return new HashMap<String, String>() {
            {
                put("!message", "message");
                put("!sourceInterfaceID", "interface_id");
            }
        };
    }

    public String getSourceInterfaceID() {
        return sourceInterfaceID;
    }
    
    public Message getMessage() {
        return this.message;
    }
    
    public DeviceInterface getSourceDeviceInterface() {
        return this.sourceInterface;
    }

    public DeviceInterface getDestinationInterface() {
        return destinationInterface;
    }
    
    public Signal routedSignalWithDestinationInterface(DeviceInterface destinationInterface) {
        Signal newSignal = new Signal();
        newSignal.message = this.message;
        newSignal.sourceInterface = this.sourceInterface;
        newSignal.destinationInterface = destinationInterface;
        return newSignal;
    }
    
    public String stringDataRepresentation() {
        JsonObject json = new JsonObject();
        
        json.addProperty("data_type", this.message.dataType);
        json.add("value", this.message.value);
        
        return json.toString();
    }
}
