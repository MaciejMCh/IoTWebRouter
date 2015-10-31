/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;
import model.Interactor;
import model.LogParser;
import org.json.JSONObject;
import websocket.requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public class LogOperation extends RequestOperation {

    private InterpretedLogOperation interpretedOperation;
    
    public LogOperation(JSONObject params, Session session) {
        super(params, session);
        try {
            this.interpretedOperation = (InterpretedLogOperation)LogRequestInterpreter.serializeOperation(params.getJSONObject("request"), session);
        } catch (InstantiationException ex) {
            Logger.getLogger(LogOperation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LogOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void performOperation() {
        this.interpretedOperation.performOperation();
//        try {
//            this.session.getBasicRemote().sendText(LogParser.parseDevices(Interactor.getInstance().enviroment.devices));
//        } catch (IOException ex) {
//            Logger.getLogger(LogOperation.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    
    
}
