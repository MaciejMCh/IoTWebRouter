/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations;

import java.util.HashMap;
import requestOperations.Admin.ConnectOperation;
import requestOperations.Log.LogOperation;

/**
 *
 * @author maciej
 */
public class AdminRequestOperationsSerializer extends RequestOperationsSerializer {

    @Override
    protected HashMap<String, Class> operationClassMap() {
        return new HashMap<String, Class>() {{
            put("log", LogOperation.class);
            put("connect", ConnectOperation.class);
        }};
    }
    
}
