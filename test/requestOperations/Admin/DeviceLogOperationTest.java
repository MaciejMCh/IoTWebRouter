/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
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

/**
 *
 * @author maciej
 */
public class DeviceLogOperationTest {
    
    public DeviceLogOperationTest() {
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
     * Test of arguments method, of class DeviceLogOperation.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"action\":\"devices\",\"device_id\":\"dev_0\",\"interface\":false}").getAsJsonObject();
            SerializableModel model = ModelSerializer.model(DeviceLogOperation.class, json);
            
            assertNotNull(model);
            assertEquals(model.getClass(), DeviceLogOperation.class);
            
            DeviceLogOperation operation = (DeviceLogOperation) model;
            
            assertNotNull(operation.interfaceOption);
            assertEquals(operation.deviceID, "dev_0");
            assertEquals(operation.interfaceOption, false);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testNoArgumentsSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"action\":\"devices\",\"interface\":true}").getAsJsonObject();
            SerializableModel model = ModelSerializer.model(DeviceLogOperation.class, json);
            
            assertNotNull(model);
            assertEquals(model.getClass(), DeviceLogOperation.class);
            
            DeviceLogOperation operation = (DeviceLogOperation) model;
            
            assertNull(operation.deviceID);
            assertNotNull(operation.interfaceOption);
            assertEquals(operation.interfaceOption, false);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
