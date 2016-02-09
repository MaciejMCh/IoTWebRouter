/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Application;

import com.google.gson.JsonObject;
import java.util.HashMap;
import requestOperations.Admin.DisconnectOperation;

/**
 *
 * @author maciej
 */
public class DisconnectInterfacesOperation extends ResponsableRequestOperation {

    protected DisconnectOperation workerOperation;

    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        HashMap<String, String> map = super.JSONKeyPathsByPropertyKey();
        map.put("!workerOperation", "connection");
        return map;
    }
    
    @Override
    public RequestResponse performReponsableOperation() {
        if (this.workerOperation.getError() == null) {
            this.workerOperation.performOperation();
            return RequestResponse.successResponse(this.requestID, new JsonObject());
        } else {
            return RequestResponse.errorResponse(this.requestID, this.workerOperation.getError().getErrorMessage());
        }
    }
    
    protected void init() {
        this.workerOperation.init();
    }
    
}
