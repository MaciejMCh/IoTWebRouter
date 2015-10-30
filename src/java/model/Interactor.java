/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Objects;
import javax.websocket.Session;
import static model.InterfaceDirection.Output;
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
    private final HashMap<Device, Session> sessionDeviceMap = new HashMap<>();
    
    public final Enviroment enviroment = new Enviroment();
    public final Router router = new Router();
    
    public Session sessionOfDevice(Device device) {
        return this.sessionDeviceMap.get(device);
    }
    
    public void registerDevice(Device device, Session session) {
        if (this.deviceSessionMap.keySet().contains(session)) {
            return;
        }
        this.deviceSessionMap.put(session, device);
        this.sessionDeviceMap.put(device, session);
        this.enviroment.addDevice(device);
        this.router.addOutputsOfDevice(device);
        
        AdminWebSocket.getInstance().deviceRegistered(device);
    }
    
    public void sessionExpired(Session session) {
        Device device = this.deviceSessionMap.get(session);
        this.enviroment.removeDevice(device);
        this.deviceSessionMap.remove(session);
        this.sessionDeviceMap.remove(device);
        AdminWebSocket.getInstance().deviceUnregistered(device);
    }
    
    public Device deviceForSession(Session session) {
        return this.deviceSessionMap.get(session);
    }
}
