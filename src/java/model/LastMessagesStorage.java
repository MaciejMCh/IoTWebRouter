/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import sun.security.jca.GetInstance;

/**
 *
 * @author maciej
 */
public class LastMessagesStorage {
    
    protected static LastMessagesStorage instance;
    public static LastMessagesStorage getInstance() {
        if (instance == null) {
            instance = new LastMessagesStorage();
        }
        return instance;
    }
    
    protected HashMap<DeviceInterface, Message> messagesByInterfaces = new HashMap<>();
    
    public void passValue(Message message, DeviceInterface outputInterface) {
        this.messagesByInterfaces.put(outputInterface, message);
    }
    
    public Message getlastMessage(DeviceInterface deviceInterface) {
        return this.messagesByInterfaces.get(deviceInterface);
    }
    
    public HashMap<DeviceInterface, Message> getAllLastMessages() {
        return this.messagesByInterfaces;
    }
    
    public void removeStoredMessagesOfDevice(Device device) {
        for (DeviceInterface deviceInterface : device.getInterfaces()) {
            this.messagesByInterfaces.remove(deviceInterface);
        }
    }
}
