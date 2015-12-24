/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Application;

import com.google.gson.JsonObject;
import model.DeviceInterface;
import model.Medium;
import requestOperations.Admin.ConnectOperation;

/**
 *
 * @author maciej
 */
public class ConnectInterfacesOperation extends ResponsableRequestOperation {

    protected ConnectOperation workerOperation;
    
    private void init() {
        //TODO: assign worker operation
    }

    @Override
    public ConversationResponse performReponsableOperation() {
        if (this.workerOperation.getError() == null) {
            this.workerOperation.performOperation();
            return ConversationResponse.successResponse(this.requestID, new JsonObject());
        } else {
            return ConversationResponse.errorResponse(this.requestID, this.workerOperation.getError().getErrorMessage());
        }
    }
    
}
