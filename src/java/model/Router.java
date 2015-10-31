/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import static model.InterfaceDirection.Output;

/**
 *
 * @author maciej
 */

public class Router {
    private final HashMap<DeviceInterface, ArrayList<DeviceInterface>> routingTable = new HashMap<>();
    
    public void addOutputsOfDevice(Device device) {
        for (DeviceInterface outputInterface : device.interfaces) {
            if (outputInterface.interfaceDirection == Output) {
                this.addOuputInterface(outputInterface);
            }
        }
    }
    
    public void removeOutputsOfDevice(Device device) {
        for (DeviceInterface outputInterface : device.interfaces) {
            if (outputInterface.interfaceDirection == Output) {
                this.removeOuputInterface(outputInterface);
            }
        }
    }
    
    public void connectInterfaces(DeviceInterface outputInterface, DeviceInterface inputInterface) {
        if (this.routingTable.containsKey(outputInterface)) {
            this.routingTable.get(outputInterface).add(inputInterface);
        }
    }
    
    private void addOuputInterface(DeviceInterface outputInterface) {
        if (this.routingTable.containsKey(outputInterface)) {
            return;
        }
        this.routingTable.put(outputInterface, new ArrayList<>());
    }
    
    private void removeOuputInterface(DeviceInterface outputInterface) {
        if (!this.routingTable.containsKey(outputInterface)) {
            return;
        }
        this.routingTable.remove(outputInterface);
    }
    
    public ArrayList<Signal> produceRoutedSignals(Signal signal) {
        if (signal.sourceInterface == null) {
            return new ArrayList<>();
        }
        
        ArrayList<Signal> routedSignals = new ArrayList<>();
        if (this.routingTable.containsKey(signal.sourceInterface)) {
            for (DeviceInterface inputInterface : this.routingTable.get(signal.sourceInterface)) {
                routedSignals.add(signal.routedSignalWithDestinationInterface(inputInterface));
            }
        }
        
        return routedSignals;
    }
}
