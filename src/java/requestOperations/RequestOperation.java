/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import com.google.gson.*;
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
    
    public RequestOperation(JsonObject params, Medium medium) {
        this.medium = medium;
        
        String sytnaxError = SyntaxValidator.validateSyntax(this.getSyntax(), params);
        if (sytnaxError != null) {
            this.error("Syntax error. " + sytnaxError);
            return;
        }
        
        this.mapJson(params);
    }
    
    protected void mapJson(JsonObject json) {
        
    }
    
    public abstract void performOperation();
    
    protected void error(String errorMessage) {
        this.error = new Error(errorMessage);
    }
    
    protected JsonObject getSyntax() {
        return new JsonObject();
    }
}
