/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import javax.websocket.Session;
import websocket.AdminWebSocket;

public class Interactor {
    private static Interactor instance = null;
    public static Interactor getInstance() {
        if(instance == null) {
            instance = new Interactor();
        }
        return instance;
    }
    
    protected final HashMap<Session, Device> sessionDeviceMap = new HashMap<>();
    protected final HashMap<Device, Session> deviceSessionMap = new HashMap<>();    
    protected final Enviroment enviroment = new Enviroment();
    protected final Router router = new Router();
    
    public Router getRouter() {
        return this.router;
    }
    
    public Enviroment getEnviroment() {
        return this.enviroment;
    }
    
    public void registerDevice(Device device, Session session) {
        if (this.sessionDeviceMap.keySet().contains(session)) {
            return;
        }
        this.sessionDeviceMap.put(session, device);
        this.deviceSessionMap.put(device, session);
        this.enviroment.addDevice(device);
        this.router.addOutputsOfDevice(device);
        
        AdminWebSocket.getInstance().deviceRegistered(device);
    }
    
    public void sessionExpired(Session session) {
        Device device = this.sessionDeviceMap.get(session);
        this.enviroment.removeDevice(device);
        this.sessionDeviceMap.remove(session);
        this.deviceSessionMap.remove(device);
        AdminWebSocket.getInstance().deviceUnregistered(device);
    }
    
    public Device deviceForSession(Session session) {
        return this.sessionDeviceMap.get(session);
    }
    
    public Session sessionOfDevice(Device device) {
        return this.deviceSessionMap.get(device);
    }
    
    public Device deviceForID(String id) {
        for (Device device : this.enviroment.devices) {
            if (device.getId().equals(id)) {
                return device;
            }
        }
        return null;
    }
}
