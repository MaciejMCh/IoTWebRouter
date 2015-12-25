/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations;

import java.util.HashMap;
import requestOperations.Device.DataOperation;
import requestOperations.Device.RegisterOperation;

/**
 *
 * @author maciej
 */
public class DeviceRequestOperationsSerializer extends RequestOperationsSerializer {

    @Override
    protected HashMap<String, Class> operationClassMap() {
        return new HashMap<String, Class>() {
            {
                put("register", RegisterOperation.class);
                put("data", DataOperation.class);
            }
        };
    }
    
}
