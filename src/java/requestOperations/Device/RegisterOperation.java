/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Device;

import model.Device;
import model.Interactor;
import com.google.gson.*;
import model.Medium;
import model.ModelSerializer;
import requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public class RegisterOperation extends RequestOperation {

    protected Device registeringDevice;
    protected String storedID;
    
    public RegisterOperation(JsonObject params, Medium medium) {
        super(params, medium);
    }

    @Override
    protected void mapJson(JsonObject json) {
        this.registeringDevice = (Device)ModelSerializer.model(Device.class, json.get("device").getAsJsonObject());
        if (json.has("stored_id")) {
            this.storedID = json.get("stored_id").getAsString();
        }
    }

    @Override
    public void performOperation() {
        if (this.storedID != null) {
            if (Interactor.getInstance().deviceForID(storedID) != null) {
                Interactor.getInstance().updateMedium(this.medium, this.storedID);
                return;
            }
        }
        Interactor.getInstance().registerDevice(this.registeringDevice, this.medium);
        JsonObject deviceIdJson = new JsonObject();
        deviceIdJson.addProperty("device_id", this.registeringDevice.getId());
        this.medium.sendMessage(deviceIdJson.toString());
    }
    
    @Override
    protected JsonObject getSyntax() {
        return new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"String\",\"interface\":[]}}").getAsJsonObject();
    }
}
