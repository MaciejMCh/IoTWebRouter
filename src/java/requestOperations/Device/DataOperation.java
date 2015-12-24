/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Device;

import java.util.ArrayList;
import model.Device;
import model.Interactor;
import model.Signal;
import java.util.HashMap;
import model.Medium;
import model.SerializableModel;
import requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public class DataOperation extends RequestOperation implements SerializableModel {

    protected ArrayList<Signal> signals;
    
    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        return new HashMap<String, String>() {
            {
                put("signals", "signals");
            }
        };
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
