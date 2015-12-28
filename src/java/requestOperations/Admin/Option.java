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
    
public class Option {
    protected String name;
    protected String description;
    protected String propertyName;
    protected String invocation;
    
    public String getName() {
        return name;
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public String getInvocation() {
        return invocation;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Option(String name, String description, String propertyName, String representation) {
        this.name = name;
        this.description = description;
        this.propertyName = propertyName;
        this.invocation = representation;
    }        
}
