/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import java.util.ArrayList;
import javax.websocket.Session;
import model.Device;
import model.DeviceInterface;
import model.Interactor;
import org.json.JSONObject;

/**
 *
 * @author maciej
 */

public class DeviceLogOperation extends InterpretedLogOperation {
    
    protected String deviceID;
    
    public DeviceLogOperation(JSONObject params, Session session) {
        super(params, session);
    }

    @Override
    public void performOperation() {
        
        int childrenLoggingDepth = 0;
        if (this.options.contains("i")) {
            childrenLoggingDepth = 1;
        }
        
        if (this.deviceID != null) {
            Device device = Interactor.getInstance().deviceForID(this.deviceID);
            if (device == null) {
                this.log("error: device with id '" + this.deviceID + "' not found.");
            } else {
                this.log(LogParser.parseDevice(device, new LogDepth(childrenLoggingDepth)));
            }
        } else {
            this.log(LogParser.parseDevices(Interactor.getInstance().getEnviroment().devices, new LogDepth(childrenLoggingDepth)));
        }
    }

    @Override
    protected ArrayList<String> propertyNames() {
        ArrayList<String> propertyNames = new ArrayList<>();
        propertyNames.add("deviceID");
        return propertyNames;
    }
}
