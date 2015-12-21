/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversation;

import com.google.gson.JsonObject;
import model.Medium;

/**
 *
 * @author maciej
 */
public class IndexDevicesOperation extends ResponsableRequestOperation {

    public IndexDevicesOperation(JsonObject params, Medium medium) {
        super(params, medium);
    }
    
    @Override
    public ConversationResponse performReponsableOperation() {
        return ConversationResponse.successResponse(this.requestID, null);
    }
    
}
