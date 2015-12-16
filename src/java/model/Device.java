/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import com.google.gson.*;

public class Device {
    public static int devicesCount = 0;
    
    protected String id;
    protected String name;
    protected ArrayList<DeviceInterface> interfaces = new ArrayList<>();
    
    public String getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    public ArrayList<DeviceInterface> getInterfaces() {
        return this.interfaces;
    }
    
    public Device(String id, String name, ArrayList<DeviceInterface> interfaces) {
        this.id = id;
        this.name = name;
        this.interfaces = interfaces;
    }
    
    public Device(JsonObject json) {
        this.id = "dev_" + devicesCount++;
        this.name = json.get("name").getAsString();
        JsonArray array = json.get("interface").getAsJsonArray();
        
        for (int i=0; i<= array.size() - 1; i++) {
            DeviceInterface deviceInterface = new DeviceInterface(array.get(i).getAsJsonObject());
            deviceInterface.parentDevice = new WeakReference<>(this);
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
