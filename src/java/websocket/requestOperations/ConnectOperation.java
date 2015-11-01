/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import javax.websocket.Session;
import model.Device;
import model.DeviceInterface;
import model.Interactor;
import org.json.JSONObject;

public class ConnectOperation extends RequestOperation {
    
    protected DeviceInterface outputInterface;
    protected DeviceInterface inputInterface;
    
    public ConnectOperation(JSONObject params, Session session) {
        super(params, session);
        
        String outputDeviceID = params.getJSONObject("output").getString("device");
        String outputInterfaceID = params.getJSONObject("output").getString("interface");
        String inputDeviceID = params.getJSONObject("input").getString("device");
        String inputInterfaceID = params.getJSONObject("input").getString("interface");
        
        Device outputDevice = Interactor.getInstance().deviceForID(outputDeviceID);
        if (outputDevice == null) {
            this.error("Device '" + outputDeviceID + "' doesn't exist.");
            return;
        }
        DeviceInterface outputInterface = outputDevice.interfaceWithID(outputInterfaceID);
        if (outputInterface == null) {
            this.error("Interface '" + outputInterfaceID + "' in device '" + outputDeviceID + "' doesn't exist.");
            return;
        }
        if (outputInterface.getInterfaceDirection() == DeviceInterface.InterfaceDirection.Input) {
            this.error("Interface '+" + outputInterfaceID + "' is 'input', but expected type is 'output'.");
            return;
        }
        
        Device inputDevice = Interactor.getInstance().deviceForID(inputDeviceID);
        if (inputDevice == null) {
            this.error("Device '" + inputDeviceID + "' doesn't exist.");
            return;
        }
        DeviceInterface inputInterface = inputDevice.interfaceWithID(inputInterfaceID);
        if (inputInterface == null) {
            this.error("Interface '" + inputInterfaceID + "' in device '" + inputDeviceID + "' doesn't exist.");
            return;
        }
        if (inputInterface.getInterfaceDirection() == DeviceInterface.InterfaceDirection.Output) {
            this.error("Interface '+" + outputInterfaceID + "' is 'output', but expected type is 'input'.");
            return;
        }
        
        this.inputInterface = inputInterface;
        this.outputInterface = outputInterface;
        
    }

    @Override
    public void performOperation() {
        Interactor.getInstance().getRouter().connectInterfaces(this.outputInterface, this.inputInterface);
    }
    
    
    
}
