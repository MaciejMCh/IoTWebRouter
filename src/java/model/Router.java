/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Session.SessionStorage;
import java.util.ArrayList;
import java.util.HashMap;
import static model.DeviceInterface.InterfaceDirection.Output;
import notificationCenter.ConnectionRemovedNotification;
import notificationCenter.NewConnectionNotification;
import notificationCenter.NotificationCenter;

/**
 *
 * @author maciej
 */

public class Router {
    protected final HashMap<DeviceInterface, ArrayList<DeviceInterface>> routingTable = new HashMap<>();

    public HashMap<DeviceInterface, ArrayList<DeviceInterface>> getRoutingTable() {
        return routingTable;
    }
    
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
        SessionStorage.getInstance().saveSessionState();
    }
    
    public ArrayList<InterfaceConnection> getInterfacesConnections() {
        ArrayList<InterfaceConnection> connections = new ArrayList<>();
        for (DeviceInterface outputInterface : this.routingTable.keySet()) {
            for (DeviceInterface inputInterface : this.routingTable.get(outputInterface)) {
                connections.add(new InterfaceConnection(inputInterface, outputInterface));
            }
        }
        return connections;
    }
    
    public void connectInterfaces(DeviceInterface outputInterface, DeviceInterface inputInterface) {
        if (this.routingTable.containsKey(outputInterface)) {
            this.routingTable.get(outputInterface).add(inputInterface);
            NotificationCenter.getInstance().notify(new NewConnectionNotification(new InterfaceConnection(inputInterface, outputInterface)));
        }
        SessionStorage.getInstance().saveSessionState();
    }
    
    public void disconnectInterfaces(DeviceInterface outputInterface, DeviceInterface inputInterface) {
        if (this.routingTable.containsKey(outputInterface)) {
            this.routingTable.get(outputInterface).remove(inputInterface);
            NotificationCenter.getInstance().notify(new ConnectionRemovedNotification(new InterfaceConnection(inputInterface, outputInterface)));
        }
        SessionStorage.getInstance().saveSessionState();
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
    
    protected void addOuputInterface(DeviceInterface outputInterface) {
        if (this.routingTable.containsKey(outputInterface)) {
            return;
        }
        this.routingTable.put(outputInterface, new ArrayList<>());
    }
    
    protected void removeOuputInterface(DeviceInterface outputInterface) {
        if (!this.routingTable.containsKey(outputInterface)) {
            return;
        }
        this.routingTable.remove(outputInterface);
    }
    
}
