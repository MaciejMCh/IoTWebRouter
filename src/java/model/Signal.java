/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.json.JSONObject;

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
    
    public Signal(JSONObject json, Device device) {
        this.message = new Message(json.getString("dataType"), json.get("value"));
        this.sourceInterface = device.interfaceWithID(json.getString("id"));
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
        JSONObject json = new JSONObject();
        
        json.append("dataType", this.message.dataType);
        json.append("value", this.message.value);
        json.append("interface", this.destinationInterface.id);
        
        return json.toString();
    }
}
