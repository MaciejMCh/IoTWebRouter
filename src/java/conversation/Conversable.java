/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversation;

/**
 *
 * @author maciej
 */
public abstract class Conversable {
    
    protected MessageHandler messageHandler;
    
    public abstract void sendMessage(String message);
    
    public void receivedMessage(String message) {
        if (this.messageHandler == null) {
            return;
        }
        this.messageHandler.onMessage(message);
    }
    
    public void handleMessage(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }
}