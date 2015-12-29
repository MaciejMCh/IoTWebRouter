/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import requestOperations.AdminRequestOperationsSerializer;

/**
 *
 * @author maciej
 */
public class HelpLogOperation extends InterpretedOperation {
    
    protected String operationName;
    protected boolean details;
    protected boolean syntax;

    @Override
    public void performOperation() {
        if (this.operationName == null) {
            String output = "log operations:";
            for (String operationName : new AdminRequestOperationsSerializer().operationClassMap().keySet()) {
                InterpretedOperation operation = null;
                try {
                    operation = (InterpretedOperation) new AdminRequestOperationsSerializer().operationClassMap().get(operationName).newInstance();
                } catch (Exception ex) {
                    this.medium.sendMessage("Internal server error. " + ex.toString());
                }
                output += "\n" + operationName + " - " + operation.description();
                if (this.syntax || this.details) {
                    output += " syntax: " + operationName + " " + operation.syntaxString();
                    if (this.details) {
                        ArrayList<Argument> arguments = operation.arguments();
                        if (!arguments.isEmpty()) {
                            output += "\n\t arguments:";
                            for (Argument argument : arguments) {
                                String requiredString = argument.getIsRequired() ? "(Required)" : "";
                                output += "\n\t\t " + argument.getPropertyName() + " - " + argument.getDescription() + " " + requiredString;
                            }                    
                        }
                        ArrayList<Option> options = operation.options();
                        if (!options.isEmpty()) {
                            output += "\n\t options:";
                            for (Option option : options) {
                                output += "\n\t\t --" + option.getName() + " [-" + option.getInvocation() + "] - " + option.getDescription();
                            }                    
                        }
                    }
                }
            }
            this.medium.sendMessage(output);
        }
    }

    @Override
    public ArrayList<Argument> arguments() {
        ArrayList<Argument> arguments = new ArrayList<>();
        arguments.add(new Argument("operation_name", "operation name", "operationName", false));
        return arguments;
    }

    @Override
    public ArrayList<Option> options() {
        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option("syntax", "logs syntax of expression", "syntax", "s"));
        options.add(new Option("details", "logs syntax of and described details of expression", "details", "d"));
        return options;
    }

    @Override
    public String description() {
        return "logs all available commands";
    }
         
}
