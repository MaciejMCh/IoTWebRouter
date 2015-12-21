/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversation;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import model.Device;
import model.DeviceInterface;
import websocket.requestOperations.Log.LogParser;

/**
 *
 * @author maciej
 */
public class JsonParser {
    public static JsonObject parseDevice(Device device) {
        JsonObject json = new JsonObject();
        json.addProperty("id", device.getId());
        json.addProperty("name", device.getName());
        json.add("interfaces", parseInterfaces(device.getInterfaces()).get("interfaces"));
        
        return json;
    }
    
    public static JsonObject parseDevices(ArrayList<Device> devices) {
        JsonArray devicesJsonArray = new JsonArray();
        for (Device device : devices) {
            devicesJsonArray.add(parseDevice(device));
        }
        JsonObject resultJson = new JsonObject();
        resultJson.add("devices", devicesJsonArray);
        return resultJson;
    }
    
    public static JsonObject parseInterface(DeviceInterface deviceInterface) {
        JsonObject json = new JsonObject();
        json.addProperty("id", deviceInterface.getId());
        json.addProperty("data_type", deviceInterface.getDataType());
        json.addProperty("direction", LogParser.parseInterfaceDirection(deviceInterface.getInterfaceDirection()));
        
        return json;
    }
    
    public static JsonObject parseInterfaces(ArrayList<DeviceInterface> interfaces) {
        JsonArray interfacesJsonArray = new JsonArray();
        for (DeviceInterface deviceInterface : interfaces) {
            interfacesJsonArray.add(parseInterface(deviceInterface));
        }
        JsonObject resultJson = new JsonObject();
        resultJson.add("interfaces", interfacesJsonArray);
        return resultJson;
    }
}
