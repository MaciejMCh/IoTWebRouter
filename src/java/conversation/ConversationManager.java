/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversation;

import java.util.HashMap;

/**
 *
 * @author maciej
 */
public class ConversationManager {
    private static ConversationManager instance = null;
    public static ConversationManager getInstance() {
        if(instance == null) {
            instance = new ConversationManager();
        }
        return instance;
    }
    
    protected HashMap<String, Conversable> conversationIDConversableMap = new HashMap();
    
    public Conversable conversableWithConversationID(String conversationID) {
        return this.conversationIDConversableMap.get(conversationID);
    }
    
    public void requestReceived(ConversationRequest conversationRequest) {
        this.sendResponse(conversationRequest.performRequestedActions());
    }
    
    public void sendResponse(ConversationResponse conversationResponse) {
        Conversable conversable = this.conversableWithConversationID(conversationResponse.conversationID);
        conversable.sendMessage(conversationResponse.jsonRepresentation().getAsString());
    }
    
    
}
