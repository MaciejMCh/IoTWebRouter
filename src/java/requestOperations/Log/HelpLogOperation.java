/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Log;

import com.google.gson.JsonObject;
import java.lang.reflect.Method;
import java.util.ArrayList;
import model.Medium;

/**
 *
 * @author maciej
 */
public class HelpLogOperation extends InterpretedOperation {
    
    protected String operationName;
    protected boolean details;
    protected boolean syntax;

    public HelpLogOperation(JsonObject params, Medium medium) {
        super(params, medium);
    }

    @Override
    public void performOperation() {
        if (this.operationName == null) {
            String output = "log operations:";
            for (String operationName : QueryJsonizer.getOperationClassMap().keySet()) {
                output += "\n" + operationName + " - " + staticEvaluation(operationName, "description");
                if (this.syntax || this.details) {
                    output += " syntax: " + operationName + " " + staticEvaluation(operationName, "syntaxString");
                    if (this.details) {
                        ArrayList<Argument> arguments = (ArrayList<Argument>)staticEvaluation(operationName, "arguments");
                        if (!arguments.isEmpty()) {
                            output += "\n\t arguments:";
                            for (Argument argument : arguments) {
                                String requiredString = argument.getIsRequired() ? "(Required)" : "";
                                output += "\n\t\t " + argument.getPropertyName() + " - " + argument.getDescription() + " " + requiredString;
                            }                    
                        }
                        ArrayList<Option> options = (ArrayList<Option>)staticEvaluation(operationName, "options");
                        if (!options.isEmpty()) {
                            output += "\n\t options:";
                            for (Option option : options) {
                                output += "\n\t\t --" + option.getName() + " [-" + option.getRepresentation() + "] - " + option.getDescription();
                            }                    
                        }
                    }
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
    
    
    
    protected Object staticEvaluation(String logOperationName, String selector) {
        try {
            Class<InterpretedOperation> clazz = QueryJsonizer.getOperationClassMap().get(logOperationName);
            Object object = clazz.getConstructor(JsonObject.class, Medium.class).newInstance(new JsonObject(), this.medium);
            Method method = clazz.getMethod(selector);
            Object result = method.invoke(object);
            return result;
        } catch (Exception e) {
            try {
                Class<InterpretedOperation> clazz = QueryJsonizer.getOperationClassMap().get(logOperationName);
                Object object = clazz.getConstructor(JsonObject.class, Medium.class).newInstance(new JsonObject(), this.medium);
                Method method = clazz.getDeclaredMethod(selector);
                Object result = method.invoke(object);
                return result;
            } catch (Exception ex) {
            
            }
        }
        return null;
    }           
}
