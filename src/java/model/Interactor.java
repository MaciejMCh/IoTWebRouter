/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import notificationCenter.DeviceConnectionErrorNotification;
import notificationCenter.DeviceReconnectNotification;
import notificationCenter.NewDeviceNotification;
import notificationCenter.NotificationCenter;

public class Interactor {
    private static Interactor instance = null;
    public static Interactor getInstance() {
        if(instance == null) {
            instance = new Interactor();
        }
        return instance;
    }
    
    protected final HashMap<Medium, Device> mediumDeviceMap = new HashMap<>();
    protected final HashMap<Device, Medium> deviceMediumMap = new HashMap<>();    
    protected final Enviroment enviroment = new Enviroment();
    protected final Router router = new Router();
    
    public Router getRouter() {
        return this.router;
    }
    
    public Enviroment getEnviroment() {
        return this.enviroment;
    }
    
    public void registerDevice(Device device, Medium medium) {
        if (this.mediumDeviceMap.keySet().contains(medium)) {
            return;
        }
        
        this.mediumDeviceMap.put(medium, device);
        this.deviceMediumMap.put(device, medium);
        this.enviroment.addDevice(device);
        this.router.addOutputsOfDevice(device);
        
        NotificationCenter.getInstance().notify(new NewDeviceNotification(device));
    }
    
    public void updateMedium(Medium medium, String deviceID) {
        Device device = this.deviceForID(deviceID);
        Medium oldMedium = this.deviceMediumMap.get(device);
        
        this.mediumDeviceMap.remove(oldMedium);
        this.mediumDeviceMap.put(medium, device);
        
        this.deviceMediumMap.replace(device, medium);
        
        NotificationCenter.getInstance().notify(new DeviceReconnectNotification(device));
    }
    
    public void mediumClosed(Medium medium) {
        Device device = this.mediumDeviceMap.get(medium);
        this.enviroment.removeDevice(device);
        this.mediumDeviceMap.remove(medium);
        this.deviceMediumMap.remove(device);
        
        NotificationCenter.getInstance().notify(new DeviceConnectionErrorNotification(device));
    }
    
    public Device deviceForMedium(Medium medium) {
        return this.mediumDeviceMap.get(medium);
    }
    
    public Medium sessionOfDevice(Device device) {
        return this.deviceMediumMap.get(device);
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
