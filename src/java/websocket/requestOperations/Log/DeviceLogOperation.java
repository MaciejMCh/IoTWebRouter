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
import com.google.gson.*;

/**
 *
 * @author maciej
 */

public class DeviceLogOperation extends InterpretedLogOperation {
    
    protected String deviceID;
    
    protected boolean interfaceOption;
    
    public DeviceLogOperation(JsonObject params, Session session) {
        super(params, session);
    }

    @Override
    public void performOperation() {
        
        int childrenLoggingDepth = 0;
        if (this.interfaceOption) {
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
    protected ArrayList<websocket.requestOperations.Log.Argument> arguments() {
        ArrayList<Argument> arguments = new ArrayList<>();
        arguments.add(new websocket.requestOperations.Log.Argument("deviceID", "Id of device you want to log.", false));
        return arguments;
    }
    
    @Override
    protected ArrayList<Option> options() {
        ArrayList<Option> arguments = new ArrayList<>();
        arguments.add(new Option("interface", "Logs all interfaces of device.", "interfaceOption", "i"));
        return arguments;
    }   

    @Override
    public String description() {
        return "informations about devices connected to server";
    }
    
    
    
}
