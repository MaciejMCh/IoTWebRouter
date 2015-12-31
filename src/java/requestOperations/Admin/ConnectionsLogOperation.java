/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import model.Device;
import model.DeviceInterface;
import model.Interactor;
import model.InterfaceConnection;
import model.Medium;

/**
 *
 * @author maciej
 */
public class ConnectionsLogOperation extends InterpretedOperation {

    protected boolean possibleInterfaces;

    @Override
    public void performOperation() {
        if (this.possibleInterfaces) {
            this.medium.sendMessage(possibleConnectionsLog());
        } else {
            this.medium.sendMessage(connectionsLog());
        }
    }

    @Override
    public ArrayList<Option> options() {
        return new ArrayList<Option>() {{
            add(new Option("possible", "logs possible connections of all interfaces", "possibleInterfaces", "p"));
        }};
    }

    @Override
    public ArrayList<Argument> arguments() {
        return new ArrayList<>();
    }

    @Override
    public String description() {
        return "logs connections between interfaces";
    }
    
    
    
    public String connectionsLog() {
        String output = "connections:";
        for (InterfaceConnection connection : Interactor.getInstance().getRouter().getInterfacesConnections()) {
            output += "\n " + LogParser.parseInterfaceConnection(connection);
        }
        return output;
    }
    
    public String possibleConnectionsLog() {
        String output = "possible connections:";
        
        ArrayList<DeviceInterface> allInterfaces = new ArrayList<>();
        for (Device device : Interactor.getInstance().getEnviroment().devices) {
            for (DeviceInterface deviceInterface : device.getInterfaces()) {
                allInterfaces.add(deviceInterface);
            }
        }
        
        if (allInterfaces.size() < 2) {
            return output;
        }
        
        ArrayList<InterfaceConnection> connections = new ArrayList<>();
        for (DeviceInterface int1 : allInterfaces) {
            for (DeviceInterface int2 : allInterfaces) {
                connections.add(new InterfaceConnection(int1, int2));
            }   
        }
        
        ArrayList<InterfaceConnection> validConnections = new ArrayList<>();
        for (InterfaceConnection connection : connections) {
            if (connection.isValid()) {
                validConnections.add(connection);
            }
        }
        
        for (InterfaceConnection connection : validConnections) {
            output += "\n " + LogParser.parseInterfaceConnection(connection);
        }
        
        return output;
    }
}
