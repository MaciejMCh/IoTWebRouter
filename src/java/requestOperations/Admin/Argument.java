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
    protected String propertyName;
    protected String description;
    
    public String getDescription() {
        return description;
    }
    
    public String getPropertyName() {
        return propertyName;
    }    
    
    public Argument(String propertyName, String description) {
        this.propertyName = propertyName;
        this.description = description;
    }
}
