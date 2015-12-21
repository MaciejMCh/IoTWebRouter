/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversation;

import com.google.gson.JsonObject;
import model.Interactor;
import model.Medium;

/**
 *
 * @author maciej
 */
public class IndexConnectionsOperation extends ResponsableRequestOperation {

    public IndexConnectionsOperation(JsonObject params, Medium medium) {
        super(params, medium);
    }

    @Override
    public ConversationResponse performReponsableOperation() {
        return ConversationResponse.successResponse(this.requestID, JsonParser.parseInterfacesConnections(Interactor.getInstance().getRouter().getInterfacesConnections()));
    }
    
}
