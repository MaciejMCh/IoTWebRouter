/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import com.google.gson.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Medium;
import websocket.requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public class LogOperation extends RequestOperation {

    protected InterpretedLogOperation interpretedOperation;
    
    public LogOperation(JsonObject params, Medium medium) {
        super(params, medium);
    }

    @Override
    protected void mapJson(JsonObject json) {
        this.interpretedOperation = (InterpretedLogOperation)LogRequestInterpreter.serializeOperation(json.get("request").getAsJsonObject(), this.medium);
    }

    @Override
    public void performOperation() {
        if (this.interpretedOperation.getError() != null) {
            this.medium.sendMessage(this.interpretedOperation.getError().getErrorMessage());
        } else {
            this.interpretedOperation.performOperation();
        }
    }

    @Override
    protected JsonObject getSyntax() {
        return new JsonParser().parse("{\"action\":\"String\",\"request\":{}}").getAsJsonObject();
    }
    
    
}
