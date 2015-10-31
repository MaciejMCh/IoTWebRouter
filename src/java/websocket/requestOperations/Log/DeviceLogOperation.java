/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket.requestOperations.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.websocket.Session;
import org.json.JSONObject;

/**
 *
 * @author maciej
 */
public class DeviceLogOperation extends InterpretedLogOperation {
    
    public String deviceID;
    
    public DeviceLogOperation(JSONObject params, Session session) {
        super(params, session);
        String sisi = "asdas";
        String sisis = "asdas";
//        set(this, "deviceID", "dupaa");
    }

    @Override
    public ArrayList<String> propertyNames() {
        ArrayList<String> propertyNames = new ArrayList<>();
        propertyNames.add("deviceID");
        return propertyNames;
    }
    
    


    
}
