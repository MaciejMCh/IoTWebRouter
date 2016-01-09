/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Application;

import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelSerializer;
import model.SerializableModel;
import model.SerializationErrorException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import requestOperations.Device.RegisterOperation;
import requestOperations.DeviceRequestOperationsSerializer;
import requestOperations.FakeMedium;

/**
 *
 * @author maciej
 */
public class ChangeDeviceTagOperationTest {
    
    public ChangeDeviceTagOperationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of JSONKeyPathsByPropertyKey method, of class ChangeDeviceTag.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new com.google.gson.JsonParser().parse("{\"action\":\"change_tag\",\"request_id\":\"app_req_1\", \"device_id\": \"dev_0\", \"tag\": \"tag\"}").getAsJsonObject();
            SerializableModel model = ModelSerializer.model(ChangeDeviceTagOperation.class, json);
            
            assertNotNull(model);
            assertEquals(model.getClass(), ChangeDeviceTagOperation.class);
            
            ChangeDeviceTagOperation operation = (ChangeDeviceTagOperation)model;
            
            assertEquals(operation.newTag, "tag");
            assertEquals(operation.deviceID, "dev_0");
            
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of performReponsableOperation method, of class ChangeDeviceTag.
     */
    @Test
    public void testPerformReponsableOperation() {
        try {
            JsonObject json = new com.google.gson.JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"in_0\"}]}}").getAsJsonObject();
            FakeMedium medium = new FakeMedium();
            RegisterOperation registerOperation = (RegisterOperation) new DeviceRequestOperationsSerializer().serializeOperation(json, medium);
            registerOperation.performOperation();
            
            JsonObject changeTagJson = new com.google.gson.JsonParser().parse("{\"action\":\"change_tag\",\"request_id\":\"app_req_1\", \"device_id\": \""+registerOperation.getRegisteringDevice().getId()+"\", \"tag\": \"tag\"}").getAsJsonObject();
            ChangeDeviceTagOperation operation = (ChangeDeviceTagOperation) ModelSerializer.model(ChangeDeviceTagOperation.class, changeTagJson);
            operation.medium = new FakeMedium();
            operation.performOperation();
            
            assertEquals(registerOperation.getRegisteringDevice().getTag(), "tag");
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
