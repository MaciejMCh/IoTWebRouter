/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

/**
 *
 * @author maciej
 */
public class Argument {
    protected String argumentName;
    protected String description;
    protected String propertyName;

    public String getArgumentName() {
        return argumentName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public Argument(String argumentName, String description, String propertyName) {
        this.argumentName = argumentName;
        this.description = description;
        this.propertyName = propertyName;
    }
}
