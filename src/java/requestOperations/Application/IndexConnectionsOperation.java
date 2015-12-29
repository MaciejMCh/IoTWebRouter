/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Application;

import model.Interactor;

/**
 *
 * @author maciej
 */
public class IndexConnectionsOperation extends ResponsableRequestOperation {

    @Override
    public RequestResponse performReponsableOperation() {
        return RequestResponse.successResponse(this.requestID, JsonParser.parseInterfacesConnections(Interactor.getInstance().getRouter().getInterfacesConnections()));
    }
    
}
