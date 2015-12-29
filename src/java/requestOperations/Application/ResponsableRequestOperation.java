/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Application;

import java.util.HashMap;
import model.SerializableModel;
import requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public abstract class ResponsableRequestOperation extends RequestOperation implements SerializableModel {
    
    protected String  requestID;
    
    public abstract ConversationResponse performReponsableOperation();

    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        return new HashMap<String, String>() {{
            put("!requestID", "request_id");
        }};
    }
    
    @Override
    public void performOperation() {
        this.medium.sendMessage(this.performReponsableOperation().jsonRepresentation().toString());
    }
    
}
