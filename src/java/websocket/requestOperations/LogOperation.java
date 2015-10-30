/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;
import model.Interactor;
import model.LogParser;
import org.json.JSONObject;

/**
 *
 * @author maciej
 */
public class LogOperation extends RequestOperation {

    public LogOperation(JSONObject params, Session session) {
        super(params, session);
    }

    @Override
    public void performOperation() {
        try {
            this.session.getBasicRemote().sendText(LogParser.parseDevices(Interactor.getInstance().enviroment.devices));
        } catch (IOException ex) {
            Logger.getLogger(LogOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
