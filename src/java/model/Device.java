/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.System.in;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author maciej
 */
public class Device {
    private String id;
    private String name;
    public ArrayList<DeviceInterface> interfaces = new ArrayList<>();
    
    public Device(String id, String name, ArrayList<DeviceInterface> interfaces) {
        this.id = id;
        this.name = name;
        this.interfaces = interfaces;
    }
    
    public Device(JSONObject json) {
        this.id = "unassigned";
        this.name = json.getString("name");
        JSONArray array = json.getJSONArray("interface");
        
        for (int i=0; i<= array.length() - 1; i++) {
            DeviceInterface deviceInterface = new DeviceInterface((JSONObject)array.get(i));
            this.interfaces.add(deviceInterface);
        }
    }
    
    public DeviceInterface interfaceWithID(String id) {
        for (DeviceInterface deviceInterface : this.interfaces) {
            if (deviceInterface.id.equals(id)) {
                return deviceInterface;
            }
        }
        return null;
    }
    
}
