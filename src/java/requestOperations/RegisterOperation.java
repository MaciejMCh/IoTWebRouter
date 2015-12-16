/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import model.Device;
import model.Interactor;
import com.google.gson.*;
import model.Medium;

/**
 *
 * @author maciej
 */
public class RegisterOperation extends RequestOperation {

    protected Device registeringDevice;
    
    public RegisterOperation(JsonObject params, Medium medium) {
        super(params, medium);
    }

    @Override
    protected void mapJson(JsonObject json) {
        this.registeringDevice = new Device(json.get("device").getAsJsonObject());
    }
    
    

    @Override
    public void performOperation() {
        Interactor.getInstance().registerDevice(this.registeringDevice, this.medium);
    }
    
    @Override
    protected JsonObject getSyntax() {
        return new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"String\",\"interface\":[]}}").getAsJsonObject();
    }
}
