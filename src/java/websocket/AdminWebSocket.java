/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Device;

/**
 *
 * @author maciej
 */
public class AdminWebSocket {
    private static AdminWebSocket instance = null;
    public static AdminWebSocket getInstance() {
        if(instance == null) {
            instance = new AdminWebSocket();
        }
        return instance;
    }
    
    public void deviceRegistered(Device device) {
        Logger.getLogger(DeviceWebSocket.class.getName()).log(Level.SEVERE, "device registered", "as");
    }
    
    public void deviceUnregistered(Device device) {
        Logger.getLogger(DeviceWebSocket.class.getName()).log(Level.SEVERE, "device unregistered", "asd");
    }
    
    public void deviceSentData(Device device) {
        Logger.getLogger(DeviceWebSocket.class.getName()).log(Level.SEVERE, "device sent data", "asd");
    }
    
    

}
