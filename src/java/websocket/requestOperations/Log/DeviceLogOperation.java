/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import java.util.ArrayList;
import javax.websocket.Session;
import model.Device;
import model.Interactor;
import org.json.JSONObject;

/**
 *
 * @author maciej
 */

public class DeviceLogOperation extends InterpretedLogOperation {
    
    public String deviceID;
    
    public DeviceLogOperation(JSONObject params, Session session) {
        super(params, session);
    }

    @Override
    public void performOperation() {
        if (this.deviceID != null) {
            Device device = Interactor.getInstance().deviceForID(this.deviceID);
            if (device == null) {
                this.log("error: device with id '" + this.deviceID + "' not found.");
            } else {
                this.log(LogParser.parseDevice(device));
            }
        } else {
            this.log(LogParser.parseDevices(Interactor.getInstance().enviroment.devices));
        }
    }

    @Override
    public ArrayList<String> propertyNames() {
        ArrayList<String> propertyNames = new ArrayList<>();
        propertyNames.add("deviceID");
        return propertyNames;
    }
}
