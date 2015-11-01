/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import java.util.ArrayList;
import model.Device;
import model.DeviceInterface;
import static model.DeviceInterface.InterfaceDirection.Input;

/**
 *
 * @author maciej
 */
public class LogParser {
    public static String parseDevices(ArrayList<Device> devices, int deepLevels) {
        String result = "";
        for (Device device : devices) {
            result = result + parseDevice(device, deepLevels) + "\n";
        }
        return result;
    }
    
    public static String parseDevice(Device device, int deepLevels) {
        String output = "id: " + device.getId() + "\tname: " + device.getName();
        if (deepLevels > 0) {
            output += "\ninterfaces:";
            for (DeviceInterface deviceInterface : device.getInterfaces()) {
                output += "\n";
                output += LogParser.parseInterface(deviceInterface, deepLevels - 1);
            }
        }
        return output;
    }
    
    public static String parseInterfaceDirection(DeviceInterface.InterfaceDirection interfaceDirection) {
        if (interfaceDirection == Input) {
            return "Input";
        } else {
            return "Output";
        }
    }
    
    public static String parseInterface(DeviceInterface deviceInterface, int deepLevels) {
        return "id: " + deviceInterface.getId() + "\tdata type: " + deviceInterface.getDataType() + "\tdirection: " + LogParser.parseInterfaceDirection(deviceInterface.getInterfaceDirection());
    }
}
