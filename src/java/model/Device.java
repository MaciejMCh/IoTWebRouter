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
    
    public boolean isDuplicate(Device device) {
        if (this == device) {
            return true;
        }
        if (!this.getName().equals(device.getName())) {
            return false;
        }
        if (this.getInterfaces().size() != device.getInterfaces().size()) {
            return false;
        }
        
        for (int i=0; i<=this.interfaces.size() - 1; i++) {
            if (!this.interfaces.get(i).getDataType().equals(device.interfaces.get(i).getDataType())) {
                return false;
            }
            if (!this.interfaces.get(i).getId().equals(device.interfaces.get(i).getId())) {
                return false;
            }
            if (!this.interfaces.get(i).getInterfaceDirection().equals(device.interfaces.get(i).getInterfaceDirection())) {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " id:" + this.id;
    }
    
    
    
}
