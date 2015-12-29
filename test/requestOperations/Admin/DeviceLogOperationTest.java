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
import model.Device;
import model.ModelSerializer;
import model.SerializableModel;
import model.SerializationErrorException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import requestOperations.AdminRequestOperationsSerializer;
import requestOperations.Device.RegisterOperation;
import requestOperations.FakeMedium;
import requestOperations.RequestOperation;

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
            assertEquals(operation.interfaceOption, true);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testAllDevicesLog() {
        
        try {
            JsonObject outputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"in_1\"}]}}").getAsJsonObject();
            RegisterOperation outputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, outputRegisterJson);
            FakeMedium outputRegisterMedium = new FakeMedium();
            outputRegisterOperation.medium = outputRegisterMedium;
            outputRegisterOperation.performOperation();
            Device device = outputRegisterOperation.getRegisteringDevice();
            
            JsonObject logJson = new JsonParser().parse("{\"action\":\"operation\",\"query\":\"devices\"}").getAsJsonObject();
            FakeMedium logMedium = new FakeMedium();
            RequestOperation operation = new AdminRequestOperationsSerializer().serializeOperation(logJson, logMedium);
            operation.performOperation();
            
            assertTrue(logMedium.message.contains("devices"));
            assertTrue(logMedium.message.contains("id:"));
            assertTrue(logMedium.message.contains("name:"));
            assertTrue(logMedium.message.contains(device.getName()));
            assertTrue(logMedium.message.contains(device.getId()));
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testAllDevicesAndInterfacesLog() {
        
        try {
            JsonObject outputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"in_1\"}]}}").getAsJsonObject();
            RegisterOperation outputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, outputRegisterJson);
            FakeMedium outputRegisterMedium = new FakeMedium();
            outputRegisterOperation.medium = outputRegisterMedium;
            outputRegisterOperation.performOperation();
            Device device = outputRegisterOperation.getRegisteringDevice();
            
            JsonObject logJson = new JsonParser().parse("{\"action\":\"operation\",\"query\":\"devices -i\"}").getAsJsonObject();
            FakeMedium logMedium = new FakeMedium();
            RequestOperation operation = new AdminRequestOperationsSerializer().serializeOperation(logJson, logMedium);
            operation.performOperation();
            
            assertTrue(logMedium.message.contains("devices"));
            assertTrue(logMedium.message.contains("id:"));
            assertTrue(logMedium.message.contains("name:"));
            assertTrue(logMedium.message.contains(device.getName()));
            assertTrue(logMedium.message.contains(device.getId()));
            assertTrue(logMedium.message.contains("interfaces"));
            assertTrue(logMedium.message.contains("data type"));
            assertTrue(logMedium.message.contains("direction"));
            assertTrue(logMedium.message.contains("in_1"));
            assertTrue(logMedium.message.contains("light"));
            assertTrue(logMedium.message.contains("output"));
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testOneDeviceLog() {
        
        try {
            JsonObject outputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"in_1\"}]}}").getAsJsonObject();
            RegisterOperation outputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, outputRegisterJson);
            FakeMedium outputRegisterMedium = new FakeMedium();
            outputRegisterOperation.medium = outputRegisterMedium;
            outputRegisterOperation.performOperation();
            Device device = outputRegisterOperation.getRegisteringDevice();
            
            JsonObject logJson = new JsonParser().parse("{\"action\":\"operation\",\"query\":\"devices " + device.getId() + "\"}").getAsJsonObject();
            FakeMedium logMedium = new FakeMedium();
            RequestOperation operation = new AdminRequestOperationsSerializer().serializeOperation(logJson, logMedium);
            operation.performOperation();
            
            assertFalse(logMedium.message.contains("devices"));
            assertTrue(logMedium.message.contains("id:"));
            assertTrue(logMedium.message.contains("name:"));
            assertTrue(logMedium.message.contains(device.getName()));
            assertTrue(logMedium.message.contains(device.getId()));
            
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testOneDeviceAndInterfacesLog() {
        
        try {
            JsonObject outputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"in_1\"}]}}").getAsJsonObject();
            RegisterOperation outputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, outputRegisterJson);
            FakeMedium outputRegisterMedium = new FakeMedium();
            outputRegisterOperation.medium = outputRegisterMedium;
            outputRegisterOperation.performOperation();
            Device device = outputRegisterOperation.getRegisteringDevice();
            
            JsonObject logJson = new JsonParser().parse("{\"action\":\"operation\",\"query\":\"devices -i " + device.getId() + "\"}").getAsJsonObject();
            FakeMedium logMedium = new FakeMedium();
            RequestOperation operation = new AdminRequestOperationsSerializer().serializeOperation(logJson, logMedium);
            operation.performOperation();
            
            System.out.println(logMedium.message);
            
            assertFalse(logMedium.message.contains("devices"));
            assertTrue(logMedium.message.contains("id:"));
            assertTrue(logMedium.message.contains("name:"));
            assertTrue(logMedium.message.contains(device.getName()));
            assertTrue(logMedium.message.contains(device.getId()));
            assertTrue(logMedium.message.contains("interfaces"));
            assertTrue(logMedium.message.contains("data type"));
            assertTrue(logMedium.message.contains("direction"));
            assertTrue(logMedium.message.contains("in_1"));
            assertTrue(logMedium.message.contains("light"));
            assertTrue(logMedium.message.contains("output"));
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
