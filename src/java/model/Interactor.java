/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import javax.websocket.Session;
import websocket.AdminWebSocket;

/**
 *
 * @author maciej
 */
public class Interactor {
    private static Interactor instance = null;
    public static Interactor getInstance() {
        if(instance == null) {
            instance = new Interactor();
        }
        return instance;
    }
    
    private final HashMap<Session, Device> deviceSessionMap = new HashMap<>();
    private final Enviroment enviroment = new Enviroment();
    
    public void registerDevice(Device device, Session session) {
        if (this.deviceSessionMap.keySet().contains(session)) {
            return;
        }
        this.deviceSessionMap.put(session, device);
        this.enviroment.addDevice(device);
        AdminWebSocket.getInstance().deviceRegistered(device);
    }
    
    public void sessionExpired(Session session) {
        Device device = this.deviceSessionMap.get(session);
        this.enviroment.removeDevice(device);
        this.deviceSessionMap.remove(session);
        AdminWebSocket.getInstance().deviceUnregistered(device);
    }
    
}
