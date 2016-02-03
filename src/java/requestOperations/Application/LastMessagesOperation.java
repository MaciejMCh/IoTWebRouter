/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Application;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map.Entry;
import model.DeviceInterface;
import model.LastMessagesStorage;
import model.Message;

/**
 *
 * @author maciej
 */
public class LastMessagesOperation extends ResponsableRequestOperation {

    @Override
    public RequestResponse performReponsableOperation() {
        JsonArray array = new JsonArray();
        for (Entry<DeviceInterface, Message> entry : LastMessagesStorage.getInstance().getAllLastMessages().entrySet()) {
            JsonObject messageJson = new JsonObject();
            messageJson.addProperty("device_id", entry.getKey().getParentDevice().getId());
            messageJson.addProperty("interface_id", entry.getKey().getId());
            messageJson.addProperty("data_type", entry.getValue().getDataType());
            messageJson.add("value", entry.getValue().getValue());
            
            array.add(messageJson);
        }
        JsonObject json = new JsonObject();
        json.add("last_messages", array);
        return RequestResponse.successResponse(requestID, json);
    }
    
}
