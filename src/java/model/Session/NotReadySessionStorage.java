/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Session;

/**
 *
 * @author maciej
 */
public class NotReadySessionStorage extends SessionStorage {

    @Override
    protected void saveSessionPayload(String payload) {
        
    }

    @Override
    protected String loadSessionPayload() {
        return "";
    }
    
}
