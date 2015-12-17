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
public interface Conversable {
    public void sendMessage(String message);
    public void handleMessage(MessageHandler messageHandler);
}