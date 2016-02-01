/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import java.util.ArrayList;
import model.Device;
import model.DeviceInterface;
import static model.DeviceInterface.InterfaceDirection.Input;
import model.Interactor;
import model.InterfaceConnection;

/**
 *
 * @author maciej
 */

class LogDepth {
    protected int currentDepth;
    protected int maxDepth;

    public int getCurrentDepth() {
        return currentDepth;
    }
    
    public LogDepth(int maxDepth) {
        this.maxDepth = maxDepth;
        this.currentDepth = maxDepth;
    }
    
    protected LogDepth(int maxDepth, int currentDepth) {
        this.maxDepth = maxDepth;
        this.currentDepth = currentDepth;
    }
    
    public LogDepth decreasedDepth() {
        return new LogDepth(this.maxDepth, this.currentDepth - 1);
    }
    
    public String getTab() {
        String tab = "";
        for (int i=0; i<= this.maxDepth - this.currentDepth - 1; i++) {
            tab += " \t ";
        }
        return tab;
    }
}

public class LogParser {
    public static String parseDevices(ArrayList<Device> devices, LogDepth depth) {
        String result = depth.getTab() + "devices\n";
        for (Device device : devices) {
            result = depth.getTab() + result + parseDevice(device, depth) + "\n";
        }
        return result;
    }
    
    public static String parseDevice(Device device, LogDepth depth) {
        String output = "id: " + device.getId() + " \t name: " + device.getName();
        output += "\t(" + (Interactor.getInstance().mediumOfDevice(device) == null ? "offline" : "online") + ")";
        if (depth.getCurrentDepth() > 0) {
            output += "\n " + depth.decreasedDepth().getTab() + "interfaces:";
            for (DeviceInterface deviceInterface : device.getInterfaces()) {
                output += "\n " + depth.getTab();
                output += LogParser.parseInterface(deviceInterface, depth.decreasedDepth());
            }
        }
        return output;
    }
    
    public static String parseInterfaceDirection(DeviceInterface.InterfaceDirection interfaceDirection) {
        if (interfaceDirection == Input) {
            return "input";
        } else {
            return "output";
        }
    }
    
    public static String parseInterface(DeviceInterface deviceInterface, LogDepth depth) {
        String result = depth.getTab() + "id: " + deviceInterface.getId() + "\t data type: " + deviceInterface.getDataType() + "\t direction: " + LogParser.parseInterfaceDirection(deviceInterface.getInterfaceDirection());
        if (depth.getCurrentDepth() > 0) {
            result = result + "\t device: " + deviceInterface.getParentDevice().getId();
        }
        return result;
    }
    
    public static String parseInterfaceConnection(InterfaceConnection interfaceConnection) {
        return interfaceConnection.getOutput().getParentDevice().getId() + "." + interfaceConnection.getOutput().getId() + " -> " + interfaceConnection.getInput().getParentDevice().getId() + "." + interfaceConnection.getInput().getId();
    }
}
