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
public class Enviroment {
    
    private static Enviroment instance = null;
    public static Enviroment getInstance() {
        if(instance == null) {
            instance = new Enviroment();
        }
        return instance;
    }
    
    private final ArrayList<Device> devices = new ArrayList<>();
}
