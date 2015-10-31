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
import model.Interactor;
import model.LogParser;
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
