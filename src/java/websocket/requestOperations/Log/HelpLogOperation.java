/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import com.google.gson.JsonObject;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.websocket.Session;

/**
 *
 * @author maciej
 */
public class HelpLogOperation extends InterpretedLogOperation {
    
    protected String operationName;
    protected boolean details;
    protected boolean syntax;

    public HelpLogOperation(JsonObject params, Session session) {
        super(params, session);
    }

    @Override
    public void performOperation() {
        if (this.operationName == null) {
            String output = "log operations:";
            for (String operationName : LogRequestInterpreter.getOperationClassMap().keySet()) {
                output += "\n" + operationName + " - " + staticStringValue(operationName, "description");
                if (this.syntax) {
                    output += " syntax: " + operationName + " " + staticStringValue(operationName, "syntaxString");
                }
            }
            this.log(output);
        }
    }

    @Override
    protected ArrayList<Argument> arguments() {
        ArrayList<Argument> arguments = new ArrayList<>();
        arguments.add(new Argument("operationName", "operation name", false));
        return arguments;
    }

    @Override
    protected ArrayList<Option> options() {
        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option("syntax", "logs syntax of expression", "syntax", "s"));
        options.add(new Option("details", "logs syntax of and described details of expression", "details", "d"));
        return options;
    }

    @Override
    public String description() {
        return "logs all available commands";
    }
    
    
    
    protected String staticStringValue(String logOperationName, String selector) {
        try {
            Class<InterpretedLogOperation> clazz = LogRequestInterpreter.getOperationClassMap().get(logOperationName);
            Object object = clazz.getConstructor(JsonObject.class, Session.class).newInstance(new JsonObject(), this.session);
            Method method = clazz.getMethod(selector);
            String result = (String)method.invoke(object);
            return result;
        } catch (Exception e) {
            
        }
        return null;
    }           
}
