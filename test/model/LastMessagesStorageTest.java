/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import requestOperations.Admin.ConnectOperation;
import requestOperations.Device.DataOperation;
import requestOperations.Device.RegisterOperation;
import requestOperations.FakeMedium;

/**
 *
 * @author maciej
 */
public class LastMessagesStorageTest {
    
    public LastMessagesStorageTest() {
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
     * Test of getInstance method, of class LastMessagesStorage.
     */
    @Test
    public void testGetInstance() {
        LastMessagesStorage result = LastMessagesStorage.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of passValue method, of class LastMessagesStorage.
     */
    

    /**
     * Test of getlastMessage method, of class LastMessagesStorage.
     */
    @Test
    public void testStoringSendMessages() {
        try {
            JsonObject outputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"in_1\"}]}}").getAsJsonObject();
            RegisterOperation outputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, outputRegisterJson);
            FakeMedium outputRegisterMedium = new FakeMedium();
            outputRegisterOperation.medium = outputRegisterMedium;
            outputRegisterOperation.performOperation();
            String outputDeviceID = outputRegisterOperation.getRegisteringDevice().getId();
            Device device = outputRegisterOperation.getRegisteringDevice();
            
            JsonObject dataJson = new JsonParser().parse("{\"action\":\"data\",\"signals\":[{\"interface_id\":\"in_1\",\"message\":{\"data_type\":\"light\",\"value\":455}}]}").getAsJsonObject();
            DataOperation dataOperation = (DataOperation) ModelSerializer.model(DataOperation.class, dataJson);
            dataOperation.medium = outputRegisterMedium;
            dataOperation.performOperation();
            
            assertNotNull(LastMessagesStorage.getInstance().getlastMessage(device.getInterfaces().get(0)));
            
            assertEquals(LastMessagesStorage.getInstance().getlastMessage(device.getInterfaces().get(0)), dataOperation.getSignals().get(0).message);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testRemovingDeviceData() {
        try {
            JsonObject outputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"in_1\"}]}}").getAsJsonObject();
            RegisterOperation outputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, outputRegisterJson);
            FakeMedium outputRegisterMedium = new FakeMedium();
            outputRegisterOperation.medium = outputRegisterMedium;
            outputRegisterOperation.performOperation();
            String outputDeviceID = outputRegisterOperation.getRegisteringDevice().getId();
            Device device = outputRegisterOperation.getRegisteringDevice();
            
            JsonObject dataJson = new JsonParser().parse("{\"action\":\"data\",\"signals\":[{\"interface_id\":\"in_1\",\"message\":{\"data_type\":\"light\",\"value\":455}}]}").getAsJsonObject();
            DataOperation dataOperation = (DataOperation) ModelSerializer.model(DataOperation.class, dataJson);
            dataOperation.medium = outputRegisterMedium;
            dataOperation.performOperation();
            
            assertNotNull(LastMessagesStorage.getInstance().getlastMessage(device.getInterfaces().get(0)));
            
            LastMessagesStorage.getInstance().removeStoredMessagesOfDevice(device);
            
            assertNull(LastMessagesStorage.getInstance().getlastMessage(device.getInterfaces().get(0)));
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
   
    
}
