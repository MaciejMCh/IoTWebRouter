/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import java.util.ArrayList;
import model.Device;
import model.Interactor;
import model.Signal;
import com.google.gson.*;
import model.Medium;

/**
 *
 * @author maciej
 */
public class DataOperation extends RequestOperation {

    protected ArrayList<Signal> signals;
    
    public DataOperation(JsonObject params, Medium medium) {
        super(params, medium);
    }

    @Override
    protected void mapJson(JsonObject json) {
        this.signals = new ArrayList<>();
        JsonArray array = json.get("interfaces").getAsJsonArray();
        
        Device sendingDevice = Interactor.getInstance().deviceForMedium(medium);
        for (Object object : array) {
            JsonObject jsonObject = (JsonObject)object;
            this.signals.add(new Signal(jsonObject, sendingDevice));
        }
    }
    
    @Override
    public void performOperation() {
        ArrayList<Signal> routedSignals = new ArrayList<>();
        for (Signal signal : this.signals) {
            routedSignals.addAll(Interactor.getInstance().getRouter().produceRoutedSignals(signal));
        }
        this.sendSignals(routedSignals);
    }
    
    protected void sendSignals(ArrayList<Signal> signals) {
        for (Signal signal : signals) {
            
            Device destinationDevice = signal.getDestinationInterface().getParentDevice();
            Medium destinationMedium = Interactor.getInstance().sessionOfDevice(destinationDevice);
            destinationMedium.sendMessage(signal.stringDataRepresentation());
        }
    }
    
}
