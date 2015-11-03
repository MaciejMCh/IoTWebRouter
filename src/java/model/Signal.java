/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.*;

/**
 *
 * @author maciej
 */
public class Signal {
    protected Message message;
    protected DeviceInterface sourceInterface;
    protected DeviceInterface destinationInterface;
    
    public Message getMessage() {
        return this.message;
    }
    
    public DeviceInterface getSourceDeviceInterface() {
        return this.sourceInterface;
    }

    public DeviceInterface getDestinationInterface() {
        return destinationInterface;
    }
    
    public Signal(JsonObject json, Device device) {
        this.message = new Message(json.get("dataType").getAsString(), json.get("value"));
        this.sourceInterface = device.interfaceWithID(json.get("id").getAsString());
    }

    private Signal() {}
    
    public Signal routedSignalWithDestinationInterface(DeviceInterface destinationInterface) {
        Signal newSignal = new Signal();
        newSignal.message = this.message;
        newSignal.sourceInterface = this.sourceInterface;
        newSignal.destinationInterface = destinationInterface;
        return newSignal;
    }
    
    public String stringDataRepresentation() {
        JsonObject json = new JsonObject();
        
        json.addProperty("dataType", this.message.dataType);
        json.addProperty("value", (String) this.message.value);
        json.addProperty("interface", this.destinationInterface.id);
        
        return json.toString();
    }
}
