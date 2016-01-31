/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import java.util.ArrayList;
import java.util.HashMap;
import model.Device;
import model.DeviceInterface;
import model.Interactor;

public class ConnectOperation extends InterpretedOperation {
    
    protected String outputDeviceID;
    protected String outputInterfaceID;
    protected String inputDeviceID;
    protected String inputInterfaceID;
    
    protected DeviceInterface outputInterface;
    protected DeviceInterface inputInterface;
    
    @Override
    public ArrayList<Argument> arguments() {
        return new ArrayList<Argument>() {
            {
                add(new Argument("output_device_id", "Output device ID.", "outputDeviceID", true));
                add(new Argument("output_interface_id", "Output interface ID.", "outputInterfaceID", true));
                add(new Argument("input_device_id", "Input device ID.", "inputDeviceID", true));
                add(new Argument("input_interface_id", "Input interface ID.", "inputInterfaceID", true));
            }};
    }

    @Override
    public ArrayList<Option> options() {
        return new ArrayList<>();
    }

    @Override
    public String description() {
        return "Connects two interfaces.";
    }

    public String getOutputDeviceID() {
        return outputDeviceID;
    }

    public String getOutputInterfaceID() {
        return outputInterfaceID;
    }

    public String getInputDeviceID() {
        return inputDeviceID;
    }

    public String getInputInterfaceID() {
        return inputInterfaceID;
    }
    
    protected void init() {
        
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
            this.error("Interface '" + outputInterfaceID + "' is 'input', but expected type is 'output'.");
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
            this.error("Interface '" + outputInterfaceID + "' is 'output', but expected type is 'input'.");
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
