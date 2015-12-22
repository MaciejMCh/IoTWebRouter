/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationCenter;

import com.google.gson.JsonObject;

/**
 *
 * @author maciej
 */
public abstract class Notification {
    
    public JsonObject jsonRepresentation() {
        JsonObject json = new JsonObject();
        json.addProperty("notification_type", this.notificationType());
        json.add("subject", this.subjectJsonRepresentation());
        return json;
    }
    
    protected abstract String notificationType();
    
    protected abstract JsonObject subjectJsonRepresentation();
}
