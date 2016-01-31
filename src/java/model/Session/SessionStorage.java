/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Session;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Device;
import model.DeviceInterface;
import model.Interactor;
import model.ModelSerializer;

/**
 *
 * @author maciej
 */
public abstract class SessionStorage {
    private static SessionStorage instance = new LocalFileSessionStorage();
    
    public Interactor targetInteractor;
    
    protected abstract void saveSessionPayload(String payload);
    protected abstract String loadSessionPayload();
    
    public void setupStorageInstance(SessionStorage newInstance) {
        if (instance != null) {
            return;
        }
       instance = newInstance;
    }
    
    public static SessionStorage getInstance() {
        return instance;
    }
    
    private Interactor getInteractor() {
        if (this.targetInteractor != null) {
            return this.targetInteractor;
        }
        return Interactor.getInstance();
    }
    
    public void loadSessionState() {
        String everything = this.loadSessionPayload();
        if (everything.length() == 0) {
            return;
        }
        
        JsonObject sessionJson = new JsonParser().parse(everything).getAsJsonObject();
        try {
            for (JsonElement deviceJson : sessionJson.get("devices").getAsJsonArray()) {
                Device device = (Device) ModelSerializer.model(Device.class, deviceJson.getAsJsonObject());
                this.getInteractor().getEnviroment().addDevice(device);
                this.getInteractor().getRouter().addOutputsOfDevice(device);
            }
            
            for (JsonElement connectionJson : sessionJson.get("interfaces_connections").getAsJsonArray()) {
                Device outputDevice = this.getInteractor().deviceForID(connectionJson.getAsJsonObject().get("output").getAsJsonObject().get("device_id").getAsString());
                DeviceInterface outputInterface = outputDevice.interfaceWithID(connectionJson.getAsJsonObject().get("output").getAsJsonObject().get("interface_id").getAsString());
                Device inputDevice = this.getInteractor().deviceForID(connectionJson.getAsJsonObject().get("input").getAsJsonObject().get("device_id").getAsString());
                DeviceInterface inputInterface = inputDevice.interfaceWithID(connectionJson.getAsJsonObject().get("input").getAsJsonObject().get("interface_id").getAsString());
                this.getInteractor().getRouter().connectInterfaces(outputInterface, inputInterface);
            }
        } catch(Exception e) {
            
        }
    }
    
    public void saveSessionState() {
            JsonObject sessionJson = new JsonObject();
            sessionJson.add("devices", requestOperations.Application.JsonParser.parseDevices(this.getInteractor().getEnviroment().devices).get("devices"));
            sessionJson.add("interfaces_connections", requestOperations.Application.JsonParser.parseInterfacesConnections(this.getInteractor().getRouter().getInterfacesConnections()).get("interfaces_connections"));
            this.saveSessionPayload(sessionJson.toString());
    }
}
