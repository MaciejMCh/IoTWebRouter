/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationCenter;

import com.google.gson.JsonObject;
import model.InterfaceConnection;
import requestOperations.Application.JsonParser;

/**
 *
 * @author maciej
 */
public class NewConnectionNotification extends Notification {

    protected InterfaceConnection interfaceConnection;
    
    public NewConnectionNotification(InterfaceConnection interfaceConnection) {
        this.interfaceConnection = interfaceConnection;
    }
    
    @Override
    protected String notificationType() {
        return "new_connection";
    }

    @Override
    protected JsonObject subjectJsonRepresentation() {
        return JsonParser.parseInterfaceConnection(this.interfaceConnection);
    }
    
}
