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
import com.google.gson.*;
import model.Medium;
import requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public class DataOperation extends RequestOperation {

    protected ArrayList<Signal> signals;
    
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
