/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Application;

import com.google.gson.JsonObject;

/**
 *
 * @author maciej
 */
public class RequestResponse {
    protected String result;
    protected JsonObject response;
    public String requestID;
    
    private RequestResponse(String requestID, String result, JsonObject response) {
        super();
        this.result = result;
        this.response = response;
        this.requestID = requestID;
    }
    
    static RequestResponse successResponse(String requestID, JsonObject result) {
        return new RequestResponse(requestID, "success", result);
    }
    
    static RequestResponse errorResponse(String requestID, String message) {
        JsonObject errorJson = new JsonObject();
        errorJson.addProperty("message", message);
        return new RequestResponse(requestID, "error", errorJson);
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
