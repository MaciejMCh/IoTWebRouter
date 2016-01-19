/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    protected Interactor() {
        super();
        this.loadEnviromentState();
    }
    
    protected synchronized void loadEnviromentState() {
        try {
            String filePath = "session.txt";
            
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder stringBuffer = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                stringBuffer.append(line);
                stringBuffer.append(System.lineSeparator());
                line = reader.readLine();
            }
            String everything = stringBuffer.toString();
            reader.close();
            JsonObject sessionJson = new JsonParser().parse(everything).getAsJsonObject();
            
            for (JsonElement deviceJson : sessionJson.get("devices").getAsJsonArray()) {
                Device device = (Device) ModelSerializer.model(Device.class, deviceJson.getAsJsonObject());
                this.enviroment.addDevice(device);
                this.router.addOutputsOfDevice(device);
            }
            
            for (JsonElement connectionJson : sessionJson.get("interfaces_connections").getAsJsonArray()) {
                Device outputDevice = this.deviceForID(connectionJson.getAsJsonObject().get("output").getAsJsonObject().get("device_id").getAsString());
                DeviceInterface outputInterface = outputDevice.interfaceWithID(connectionJson.getAsJsonObject().get("output").getAsJsonObject().get("interface_id").getAsString());
                Device inputDevice = this.deviceForID(connectionJson.getAsJsonObject().get("input").getAsJsonObject().get("device_id").getAsString());
                DeviceInterface inputInterface = inputDevice.interfaceWithID(connectionJson.getAsJsonObject().get("input").getAsJsonObject().get("interface_id").getAsString());
                this.router.connectInterfaces(outputInterface, inputInterface);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Interactor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void saveEnviromentState() {
        try {
            JsonObject sessionJson = new JsonObject();
            sessionJson.add("devices", requestOperations.Application.JsonParser.parseDevices(this.enviroment.devices).get("devices"));
            sessionJson.add("interfaces_connections", requestOperations.Application.JsonParser.parseInterfacesConnections(this.router.getInterfacesConnections()).get("interfaces_connections"));
            String filePath = "session.txt";
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");
            writer.print(sessionJson.toString());
            writer.close();
        } catch (Exception ex) {
            Logger.getLogger(Interactor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
        
        this.saveEnviromentState();
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
        this.mediumDeviceMap.remove(medium);
        this.deviceMediumMap.remove(device);
        
        NotificationCenter.getInstance().notify(new DeviceConnectionErrorNotification(device));
    }
    
    public Device deviceForMedium(Medium medium) {
        return this.mediumDeviceMap.get(medium);
    }
    
    public Medium mediumOfDevice(Device device) {
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
