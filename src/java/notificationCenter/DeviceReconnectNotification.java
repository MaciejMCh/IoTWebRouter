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
public class DeviceReconnectNotification extends NewDeviceNotification {

    public DeviceReconnectNotification(Device device) {
        super(device);
    }

    @Override
    protected String notificationType() {
        return "device_reconnect";
    }
}
