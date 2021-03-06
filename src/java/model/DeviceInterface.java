/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.lang.ref.WeakReference;
import static model.DeviceInterface.InterfaceDirection.Input;
import static model.DeviceInterface.InterfaceDirection.Output;
import java.util.HashMap;

public class DeviceInterface implements SerializableModel {
    
    public enum InterfaceDirection {
        Input, Output
    }
    
    protected String id;
    protected String dataType;
    protected InterfaceDirection interfaceDirection;
    protected WeakReference<Device> parentDevice;
    
    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        return new HashMap<String, String>() {
            {
                put("!dataType", "data_type");
                put("!interfaceDirection", "direction");
                put("!id", "id");
            }
        };
    }
    
    public HashMap<String, InterfaceDirection> interfaceDirectionEnumMap() {
        return new HashMap<String, InterfaceDirection>() {
            {
                put("input", Input);
                put("output", Output);
            }
        };
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getDataType() {
        return this.dataType;
    }
    
    public InterfaceDirection getInterfaceDirection() {
        return this.interfaceDirection;
    }
    
    public Device getParentDevice() {
        return this.parentDevice.get();
    }

    @Override
    public String toString() {
        String s = "DeviceInterface id:" + this.id;
        if (this.getParentDevice() != null) {
            s += " device: " + this.getParentDevice().getId();
        }
        return s;
    }
    
    
}
