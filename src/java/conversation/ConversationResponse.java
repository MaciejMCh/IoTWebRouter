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
public class ConversationResponse {
    protected String result;
    protected JsonObject response;
    public String requestID;
    public String conversationID;
    
    private ConversationResponse(ConversationRequest conversationRequest, String result, JsonObject response) {
        super();
        this.result = result;
        this.response = response;
        this.requestID = conversationRequest.requestID;
        this.conversationID = conversationRequest.conversationID;
    }
    
    static ConversationResponse successResponse(ConversationRequest conversationRequest, JsonObject result) {
        return new ConversationResponse(conversationRequest, "success", result);
    }
    
    static ConversationResponse errorResponse(ConversationRequest conversationRequest, JsonObject result) {
        return new ConversationResponse(conversationRequest, "error", result);
    }
    
    public JsonObject jsonRepresentation() {
        JsonObject json = new JsonObject();
        json.addProperty("result", this.result);
        json.addProperty("requestID", this.requestID);
        if (this.result != null) {
            json.add("response", this.response);
        }
        return json;
    }
}
