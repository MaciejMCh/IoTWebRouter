/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Device;

import model.Device;
import model.Interactor;
import com.google.gson.*;
import java.util.HashMap;
import model.SerializableModel;
import requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public class RegisterOperation extends RequestOperation implements SerializableModel {

    protected Device registeringDevice;
    protected String storedID;
    
    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        return new HashMap<String, String>() {
            {
                put("!registeringDevice", "device");
                put("storedID", "stored_id");
            }
        };
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
    
}
