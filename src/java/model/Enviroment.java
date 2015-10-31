/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

public class Enviroment {
    public final ArrayList<Device> devices = new ArrayList<>();
    
    public void addDevice(Device device) {
        this.devices.add(device);
    }
    
    public void removeDevice(Device device) {
        this.devices.remove(device);
    }
}
