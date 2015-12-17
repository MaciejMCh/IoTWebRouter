/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import model.Device;
import model.DeviceInterface;
import model.Interactor;
import model.Medium;

/**
 *
 * @author maciej
 */
public class ConnectionsLogOperation extends InterpretedLogOperation {

    protected boolean possibleInterfaces;
    
    public ConnectionsLogOperation(JsonObject params, Medium medium) {
        super(params, medium);
    }

    @Override
    public void performOperation() {
        if (this.possibleInterfaces) {
            this.log(possibleConnectionsLog());
        } else {
            this.log(connectionsLog());
        }
    }

    @Override
    protected ArrayList<Option> options() {
        ArrayList<Option> options = super.options();
        options.add(new Option("possible", "logs possible connections of all interfaces", "possibleInterfaces", "p"));
        return options;
    }

    @Override
    protected ArrayList<Argument> arguments() {
        return new ArrayList<>();
    }

    @Override
    public String description() {
        return "logs connections between interfaces";
    }
    
    
    
    public String connectionsLog() {
        String output = "connections:";
        
        HashMap<DeviceInterface, ArrayList<DeviceInterface>> routingTable = Interactor.getInstance().getRouter().getRoutingTable();
        
        ArrayList<InterfaceConnection> connections = new ArrayList<>();
        for (DeviceInterface outputInterface : routingTable.keySet()) {
            for (DeviceInterface inputInterface : routingTable.get(outputInterface)) {
                connections.add(new InterfaceConnection(inputInterface, outputInterface));
            }
        }
        for (InterfaceConnection connection : connections) {
            output += "\n " + connection.log();
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
            output += "\n " + connection.log();
        }
        
        return output;
    }
    
    class InterfaceConnection {
        public DeviceInterface input;
        public DeviceInterface output;
        public InterfaceConnection(DeviceInterface input, DeviceInterface output) {
            this.input = input;
            this.output = output;
        }
        public boolean isValid() {
            if (input == output) {
                return false;
            }
            
            if (input.getInterfaceDirection() == DeviceInterface.InterfaceDirection.Output) {
                return false;
            }
            
            if (output.getInterfaceDirection() == DeviceInterface.InterfaceDirection.Input) {
                return false;
            }
            
            if (!output.getDataType().equals(input.getDataType())) {
                return false;
            }
            
            return true;
        }
        public String log() {
            return this.output.getParentDevice().getId() + "." + this.output.getId() + " -> " + this.input.getParentDevice().getId() + "." + this.input.getId();
        }
    }
    
}