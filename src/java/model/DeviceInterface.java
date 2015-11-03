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

public class DeviceInterface {
    
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
    
    public DeviceInterface(JsonObject json) {
        this.id = json.get("id").getAsString();
        this.dataType = json.get("dataType").getAsString();
        String interfaceDirection = json.get("direction").getAsString();
        if ("input".equals(interfaceDirection)) {
            this.interfaceDirection = Input;
        } else {
            this.interfaceDirection = Output;
        }
    }
}
