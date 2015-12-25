/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Log;

import com.google.gson.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Medium;
import model.SerializableModel;
import requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public class LogOperation extends RequestOperation implements SerializableModel {
    
    protected InterpretedLogOperation interpretedOperation;
    
    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        return new HashMap<String, String>() {{
            put("interpretedOperation", "request");
        }};
    }
    
    @Override
    public void performOperation() {
        if (this.interpretedOperation.getError() != null) {
            this.medium.sendMessage(this.interpretedOperation.getError().getErrorMessage());
        } else {
            this.interpretedOperation.performOperation();
        }
    }
    
}
