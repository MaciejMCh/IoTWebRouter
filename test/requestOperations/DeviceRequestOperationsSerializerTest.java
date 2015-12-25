/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import requestOperations.Device.DataOperation;
import requestOperations.Device.FakeMedium;
import requestOperations.Device.RegisterOperation;

/**
 *
 * @author maciej
 */
public class DeviceRequestOperationsSerializerTest {
    
    public DeviceRequestOperationsSerializerTest() {
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
     * Test of operationClassMap method, of class DeviceRequestOperationsSerializer.
     */
    @Test
    public void testSerializeRegisterOperation() {
        JsonObject json = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"in_0\"}]}}").getAsJsonObject();
        FakeMedium medium = new FakeMedium();
        
        RegisterOperation registerOperation = (RegisterOperation) new DeviceRequestOperationsSerializer().serializeOperation(json, medium);
        assertNotNull(registerOperation);
        assertEquals(registerOperation.getClass(), RegisterOperation.class);
    }
    
    @Test
    public void testSerializeDataOperation() {
        JsonObject json = new JsonParser().parse("{\"action\":\"data\",\"signals\":[{\"interface_id\":\"in_1\",\"message\":{\"data_type\":\"light\",\"value\":455}}]}").getAsJsonObject();
        FakeMedium medium = new FakeMedium();
        
        DataOperation dataOperation = (DataOperation) new DeviceRequestOperationsSerializer().serializeOperation(json, medium);
        assertNotNull(dataOperation);
        assertEquals(dataOperation.getClass(), DataOperation.class);
    }
}
