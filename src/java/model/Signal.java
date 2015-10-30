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
    Message message;
    DeviceInterface sourceInterface;
    DeviceInterface destinationInterface;
    
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
}
