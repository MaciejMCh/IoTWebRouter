/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Application;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import model.Device;
import model.DeviceInterface;
import model.InterfaceConnection;
import requestOperations.Admin.LogParser;

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
    
    public static JsonObject parseInterfaceConnection(InterfaceConnection interfaceConnection) {
        JsonObject outputJson = new JsonObject();
        outputJson.addProperty("interface_id", interfaceConnection.getOutput().getId());
        outputJson.addProperty("device_id", interfaceConnection.getOutput().getParentDevice().getId());
        
        JsonObject inputJson = new JsonObject();
        inputJson.addProperty("interface_id", interfaceConnection.getInput().getId());
        inputJson.addProperty("device_id", interfaceConnection.getInput().getParentDevice().getId());
        
        JsonObject resultJson = new JsonObject();
        resultJson.add("output", outputJson);
        resultJson.add("input", inputJson);
        
        return resultJson;
    }
    
    public static JsonObject parseInterfacesConnections(ArrayList<InterfaceConnection> interfacesConnections) {
        JsonArray interfacesConnectionsJsonArray = new JsonArray();
        for (InterfaceConnection interfacesConnection : interfacesConnections) {
            interfacesConnectionsJsonArray.add(parseInterfaceConnection(interfacesConnection));
        }
        JsonObject resultJson = new JsonObject();
        resultJson.add("interfaces_connections", interfacesConnectionsJsonArray);
        return resultJson;
    }
}
