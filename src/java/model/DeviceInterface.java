/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.lang.ref.WeakReference;
import static model.DeviceInterface.InterfaceDirection.Input;
import static model.DeviceInterface.InterfaceDirection.Output;
import com.google.gson.*;
import java.util.HashMap;

public class DeviceInterface implements SerializableModel {

    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        return new HashMap<String, String>() {
            {
                put("dataType", "data_type");
                put("interfaceDirection", "direction");
            }
        };
    }
    
    public enum InterfaceDirection {
        Input, Output
    }
    
    protected String id;
    protected String dataType;
    protected InterfaceDirection interfaceDirection;
    protected WeakReference<Device> parentDevice;
    
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
}
