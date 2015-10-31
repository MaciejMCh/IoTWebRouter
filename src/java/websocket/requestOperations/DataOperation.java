/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;
import model.Device;
import model.Interactor;
import model.Signal;
import org.json.JSONArray;
import org.json.JSONObject;
import websocket.AdminWebSocket;

/**
 *
 * @author maciej
 */
public class DataOperation extends RequestOperation {

    protected ArrayList<Signal> signals = new ArrayList<>();
    
    public DataOperation(JSONObject params, Session session) {
        super(params, session);
        JSONArray array = params.getJSONArray("interfaces");
        
        Device sendingDevice = Interactor.getInstance().deviceForSession(session);
        for (Object object : array) {
            JSONObject json = (JSONObject)object;
            this.signals.add(new Signal(json, sendingDevice));
        }
    }
    
    @Override
    public void performOperation() {
        ArrayList<Signal> routedSignals = new ArrayList<>();
        for (Signal signal : this.signals) {
            routedSignals.addAll(Interactor.getInstance().getRouter().produceRoutedSignals(signal));
        }
        this.sendSignals(routedSignals);
        AdminWebSocket.getInstance().deviceSentData(null);
    }
    
    protected void sendSignals(ArrayList<Signal> signals) {
        for (Signal signal : signals) {
            
            Device destinationDevice = signal.getDestinationInterface().getParentDevice();
            Session destinationSession = Interactor.getInstance().sessionOfDevice(destinationDevice);
            try {
                destinationSession.getBasicRemote().sendText(signal.stringDataRepresentation());
            } catch (IOException ex) {
                Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
