/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Application;

import java.util.HashMap;
import model.Device;
import model.Interactor;

/**
 *
 * @author maciej
 */
public class ChangeDeviceTagOperation extends ResponsableRequestOperation {

    protected String deviceID;
    protected String newTag;
    
    @Override
    public HashMap<String, String> JSONKeyPathsByPropertyKey() {
        HashMap<String, String> map = super.JSONKeyPathsByPropertyKey();
        map.put("!deviceID", "device_id");
        map.put("!newTag", "tag");
        
        return map;
    }
    
    @Override
    public RequestResponse performReponsableOperation() {
        Device device = Interactor.getInstance().deviceForID(this.deviceID);
        device.changeTag(newTag);
        return RequestResponse.successResponse(requestID, null);
    }
    
}
