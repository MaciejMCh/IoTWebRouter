/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import java.util.ArrayList;
import model.Device;

/**
 *
 * @author maciej
 */
public class LogParser {
    public static String parseDevices(ArrayList<Device> devices) {
        String result = "";
        for (Device device : devices) {
            result = result + parseDevice(device) + "\n";
        }
        return result;
    }
    
    public static String parseDevice(Device device) {
        return "id: " + device.getId() + "\tname: " + device.getName();
    }
}
