/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import javax.websocket.Session;
import org.json.JSONObject;
import websocket.requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public class LogOperation extends RequestOperation {

    private InterpretedLogOperation interpretedOperation;
    
    public LogOperation(JSONObject params, Session session) {
        super(params, session);
        this.interpretedOperation = (InterpretedLogOperation)LogRequestInterpreter.serializeOperation(params.getJSONObject("request"), session);
    }

    @Override
    public void performOperation() {
        this.interpretedOperation.performOperation();
    }
}
