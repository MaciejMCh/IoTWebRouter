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
public class ErrorOperation extends RequestOperation {

    public static ErrorOperation internalServerErrorOperation(Medium medium) {
        return new ErrorOperation("Internal server error.", medium);
    }
    
    protected String errorMessage;
    
    public ErrorOperation(String errorMessage, Medium medium) {
        super();
        this.errorMessage = errorMessage;
    }

    @Override
    public void performOperation() {
        this.medium.sendMessage(this.errorMessage);
    }
    
}
