/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import java.util.ArrayList;
import model.Device;
import model.Interactor;

/**
 *
 * @author maciej
 */

public class DeviceLogOperation extends InterpretedOperation {
    
    protected String deviceID;
    protected boolean interfaceOption;
    
    @Override
    public ArrayList<Argument> arguments() {
        ArrayList<Argument> arguments = new ArrayList<>();
        arguments.add(new Argument("device_id", "Id of device you want to log.", "deviceID", false));
        return arguments;
    }
    
    @Override
    public ArrayList<Option> options() {
        ArrayList<Option> arguments = new ArrayList<>();
        arguments.add(new Option("interface", "Logs all interfaces of device.", "interfaceOption", "i"));
        return arguments;
    }   

    @Override
    public String description() {
        return "informations about devices connected to server";
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
                this.medium.sendMessage("error: device with id '" + this.deviceID + "' not found.");
            } else {
                this.medium.sendMessage(LogParser.parseDevice(device, new LogDepth(childrenLoggingDepth)));
            }
        } else {
            this.medium.sendMessage(LogParser.parseDevices(Interactor.getInstance().getEnviroment().devices, new LogDepth(childrenLoggingDepth)));
        }
    }
}
