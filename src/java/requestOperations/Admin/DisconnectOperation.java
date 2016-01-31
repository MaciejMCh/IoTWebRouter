/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import model.Interactor;

/**
 *
 * @author maciej
 */
public class DisconnectOperation extends ConnectOperation {
    @Override
    public void performOperation() {
        Interactor.getInstance().getRouter().disconnectInterfaces(this.outputInterface, this.inputInterface);
    }
}
