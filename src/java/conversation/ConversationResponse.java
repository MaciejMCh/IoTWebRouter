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
    
    private ConversationResponse(String requestID, String result, JsonObject response) {
        super();
        this.result = result;
        this.response = response;
        this.requestID = requestID;
    }
    
    static ConversationResponse successResponse(String requestID, JsonObject result) {
        return new ConversationResponse(requestID, "success", result);
    }
    
    static ConversationResponse errorResponse(String requestID, JsonObject result) {
        return new ConversationResponse(requestID, "error", result);
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
