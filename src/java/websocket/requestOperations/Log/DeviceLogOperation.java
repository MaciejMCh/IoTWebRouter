/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                try {
                    this.session.getBasicRemote().sendText("error: device with id '" + this.deviceID + "' not found.");
                } catch (IOException ex) {
                    Logger.getLogger(DeviceLogOperation.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    this.session.getBasicRemote().sendText(LogParser.parseDevice(device));
                } catch (IOException ex) {
                    Logger.getLogger(DeviceLogOperation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                this.session.getBasicRemote().sendText(LogParser.parseDevices(Interactor.getInstance().enviroment.devices));
            } catch (IOException ex) {
                Logger.getLogger(DeviceLogOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

    @Override
    public ArrayList<String> propertyNames() {
        ArrayList<String> propertyNames = new ArrayList<>();
        propertyNames.add("deviceID");
        return propertyNames;
    }
    
    


    
}
