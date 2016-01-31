/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationCenter;

import model.InterfaceConnection;

/**
 *
 * @author maciej
 */
public class ConnectionRemovedNotification extends NewConnectionNotification {

    public ConnectionRemovedNotification(InterfaceConnection interfaceConnection) {
        super(interfaceConnection);
    }
    
    @Override
    protected String notificationType() {
        return "connection_removed";
    }
    
}
