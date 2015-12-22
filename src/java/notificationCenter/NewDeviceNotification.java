/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationCenter;

import com.google.gson.JsonObject;
import model.Device;
import requestOperations.Application.JsonParser;

/**
 *
 * @author maciej
 */
public class NewDeviceNotification extends Notification {

    protected Device device;

    public NewDeviceNotification(Device device) {
        super();
        this.device = device;
    }

    @Override
    protected String notificationType() {
        return "new_device";
    }

    @Override
    protected JsonObject subjectJsonRepresentation() {
        return JsonParser.parseDevice(this.device);
    }
}
