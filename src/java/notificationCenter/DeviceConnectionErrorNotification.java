/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationCenter;

import model.Device;

/**
 *
 * @author maciej
 */
public class DeviceConnectionErrorNotification extends NewDeviceNotification {

    public DeviceConnectionErrorNotification(Device device) {
        super(device);
    }

    @Override
    protected String notificationType() {
        return "device_connection_error";
    }
    
}
