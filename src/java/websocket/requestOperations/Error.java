/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations;

/**
 *
 * @author maciej
 */
public class Error {
    protected String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
    
    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}