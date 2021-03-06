/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations;

import com.google.gson.JsonObject;
import java.util.HashMap;
import model.Medium;
import requestOperations.Admin.ConnectOperation;
import requestOperations.Admin.ConnectionsLogOperation;
import requestOperations.Admin.DeviceLogOperation;
import requestOperations.Admin.DisconnectOperation;
import requestOperations.Admin.HelpLogOperation;
import requestOperations.Admin.QueryJsonizer;
import requestOperations.Admin.RestartOperation;

/**
 *
 * @author maciej
 */
public class AdminRequestOperationsSerializer extends RequestOperationsSerializer {

    @Override
    public HashMap<String, Class> operationClassMap() {
        return new HashMap<String, Class>() {{
            put("connect", ConnectOperation.class);
            put("disconnect", DisconnectOperation.class);
            put("devices", DeviceLogOperation.class);
            put("connections", ConnectionsLogOperation.class);
            put("help", HelpLogOperation.class);
            put("restart", RestartOperation.class);
        }};
    }

    @Override
    public RequestOperation serializeOperation(JsonObject json, Medium medium) {
        if (!json.has("action")) {
            return new ErrorOperation("'action' field is missing.", medium);
        }
        if (!json.has("query")) {
            return new ErrorOperation("'query' field is missing.", medium);
        }
        JsonObject jsonizedQuery = QueryJsonizer.jsonizeQuery(json.get("query").getAsString(), operationClassMap());
        return super.serializeOperation(jsonizedQuery, medium);
    }
    
    
    
}
