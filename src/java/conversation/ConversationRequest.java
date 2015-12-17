/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversation;

import com.google.gson.JsonObject;

/**
 *
 * @author maciej
 */
public abstract class ConversationRequest {
    
    public String requestID;
    public String conversationID;
    
    public ConversationRequest(JsonObject json) {
        
    }
    
    public abstract ConversationResponse performRequestedActions();
    
}
