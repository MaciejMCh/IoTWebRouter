/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import javax.websocket.Session;
import com.google.gson.*;
import websocket.requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public class LogOperation extends RequestOperation {

    protected InterpretedLogOperation interpretedOperation;
    
    public LogOperation(JsonObject params, Session session) {
        super(params, session);
    }

    @Override
    protected void mapJson(JsonObject json) {
        this.interpretedOperation = (InterpretedLogOperation)LogRequestInterpreter.serializeOperation(json.get("request").getAsJsonObject(), session);
    }

    @Override
    public void performOperation() {
        this.interpretedOperation.performOperation();
    }

    @Override
    protected JsonObject getSyntax() {
        return new JsonParser().parse("{\"action\":\"String\",\"request\":{}}").getAsJsonObject();
    }
    
    
}
