/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationCenter;

import java.util.ArrayList;
import model.Medium;

/**
 *
 * @author maciej
 */
public class NotificationCenter {
    private static NotificationCenter instance = null;
    public static NotificationCenter getInstance() {
        if(instance == null) {
            instance = new NotificationCenter();
        }
        return instance;
    }
    
    protected ArrayList<Medium> listeningMediums = new ArrayList<>();
    
    public void addMedium(Medium medium) {
        this.listeningMediums.add(medium);
    }
    
    public void removeMedium(Medium medium) {
        this.listeningMediums.remove(medium);
    }
    
    public void notify(Notification notification) {
        for (Medium listeningMedium : this.listeningMediums) {
            listeningMedium.sendMessage(notification.jsonRepresentation().toString());
        }
    }
}
