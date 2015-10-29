/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author maciej
 */
public class Device {
    private String id;
    private String name;
    private final ArrayList<DeviceInterface> interfaces = new ArrayList<>();
}
