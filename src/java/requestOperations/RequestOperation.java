/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations;

import model.Medium;

/**
 *
 * @author maciej
 */
public abstract class RequestOperation {
    
    protected Medium medium;
    protected Error error;

    public Error getError() {
        return error;
    }
    
    public RequestOperation() {
        
    }
    
    public abstract void performOperation();
    
    protected void error(String errorMessage) {
        this.error = new Error(errorMessage);
    }
}
