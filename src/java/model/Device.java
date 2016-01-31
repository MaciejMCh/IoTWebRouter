/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

public class Device implements SerializableModel {
    public static int devicesCount = 0;
    
    protected String id;
    protected String name;
    protected String tag;
    protected ArrayList<DeviceInterface> interfaces = new ArrayList<>();
    
    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        return new HashMap<String, String>() {
            {
                put("name", "name");
                put("!interfaces", "interfaces");
                put("id", "id");
            }
        };
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    public ArrayList<DeviceInterface> getInterfaces() {
        return this.interfaces;
    }

    public String getTag() {
        return tag;
    }

    public void changeTag(String tag) {
        this.tag = tag;
    }
    
    private void init() {
        if (this.id == null) {
            this.id = "dev_" + devicesCount++;
        }
        for (DeviceInterface deviceInterface : interfaces) {
            deviceInterface.parentDevice = new WeakReference<>(this);
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

    @Override
    public String toString() {
        return super.toString() + " id:" + this.id;
    }
    
    
    
}
