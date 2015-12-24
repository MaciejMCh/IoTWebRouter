package requestOperations.Device;


import model.Medium;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maciej
 */
public class FakeMedium implements Medium {

    public String message;
    
    @Override
    public void sendMessage(String message) {
        this.message = message;
    }
    
}
