/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import javax.websocket.Session;
import model.Device;
import model.Interactor;
import org.json.JSONObject;

/**
 *
 * @author maciej
 */
public class RegisterOperation extends RequestOperation {

    protected Device registeringDevice;
    
    public RegisterOperation(JSONObject params, Session session) {
        super(params, session);
        this.registeringDevice = new Device(params.getJSONObject("device"));
    }

    @Override
    public void performOperation() {
        Interactor.getInstance().registerDevice(this.registeringDevice, this.session);
    }
    
}
