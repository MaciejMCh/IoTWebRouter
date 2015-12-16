/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.*;
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
    
    public ErrorOperation(JsonObject params, Medium medium) {
        super(params, medium);
    }
    
    public ErrorOperation(String errorMessage, Medium medium) {
        super(null, medium);
        this.errorMessage = errorMessage;
    }

    @Override
    public void performOperation() {
        this.medium.sendMessage(this.errorMessage);
    }
    
}
