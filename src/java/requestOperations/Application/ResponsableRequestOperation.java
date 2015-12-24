/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Application;

import requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public abstract class ResponsableRequestOperation extends RequestOperation {
    
    protected String  requestID;
    
    public abstract ConversationResponse performReponsableOperation();
    
    @Override
    public void performOperation() {
        this.medium.sendMessage(this.performReponsableOperation().jsonRepresentation().toString());
    }
    
}
