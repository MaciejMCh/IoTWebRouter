/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations;

import java.util.HashMap;
import requestOperations.Application.ChangeDeviceTagOperation;
import requestOperations.Application.ConnectInterfacesOperation;
import requestOperations.Application.IndexConnectionsOperation;
import requestOperations.Application.IndexDevicesOperation;

/**
 *
 * @author maciej
 */
public class MobileRequestOperationsSerializer extends RequestOperationsSerializer {

    @Override
    protected HashMap<String, Class> operationClassMap() {
        return new HashMap<String, Class>() {
            {
                put("index_devices", IndexDevicesOperation.class);
                put("index_connections", IndexConnectionsOperation.class);
                put("connect_interfaces", ConnectInterfacesOperation.class);
                put("change_tag", ChangeDeviceTagOperation.class);
            }
        };
    }
    
}
