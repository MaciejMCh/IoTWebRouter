/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversation;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Medium;
import websocket.requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public abstract class ResponsableRequestOperation extends RequestOperation {
    
    protected String  requestID;
    
    public abstract ConversationResponse performReponsableOperation();
    
    public ResponsableRequestOperation(JsonObject params, Medium medium) {
        super(params, medium);
    }
    
    @Override
    protected JsonObject getSyntax() {
        return new JsonParser().parse("{\"action\":\"String\",\"request_id\":\"String\"}").getAsJsonObject();
    }

    @Override
    protected void mapJson(JsonObject json) {
        this.requestID = json.get("request_id").getAsString();
    }
    
    @Override
    public void performOperation() {
        this.medium.sendMessage(this.performReponsableOperation().jsonRepresentation().toString());
    }
    
}
