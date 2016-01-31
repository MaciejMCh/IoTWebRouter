/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import java.util.ArrayList;
import model.Interactor;

/**
 *
 * @author maciej
 */
public class RestartOperation extends InterpretedOperation {

    @Override
    public ArrayList<Argument> arguments() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<Option> options() {
        return new ArrayList<>();
    }

    @Override
    public String description() {
        return "Disconnects all devices, unregisters all devices, breaks all connections.";
    }

    @Override
    public void performOperation() {
        Interactor.getInstance().restart();
    }
    
}
