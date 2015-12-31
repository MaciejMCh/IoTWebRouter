/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class InterfaceConnectionTest {
    
    public InterfaceConnectionTest() {
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
     * Test of isValid method, of class InterfaceConnection.
     */
    @Test
    public void testInitialization() {
        try {
            JsonObject inputJson = new JsonParser().parse("{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface input = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, inputJson);
            JsonObject outputJson = new JsonParser().parse("{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface output = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, outputJson);
            
            InterfaceConnection connection = new InterfaceConnection(input, output);
            
            assertEquals(connection.getInput(), input);
            assertEquals(connection.getOutput(), output);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of log method, of class InterfaceConnection.
     */
    @Test
    public void testValid() {
        try {
            JsonObject inputJson = new JsonParser().parse("{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface input = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, inputJson);
            JsonObject outputJson = new JsonParser().parse("{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface output = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, outputJson);
            
            InterfaceConnection connection = new InterfaceConnection(input, output);
            
            assertTrue(connection.isValid());
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testInputIsOutput() {
        try {
            JsonObject inputJson = new JsonParser().parse("{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface input = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, inputJson);
            JsonObject outputJson = new JsonParser().parse("{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface output = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, outputJson);
            
            InterfaceConnection connection = new InterfaceConnection(input, output);
            
            assertFalse(connection.isValid());
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testOutputIsInput() {
        try {
            JsonObject inputJson = new JsonParser().parse("{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface input = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, inputJson);
            JsonObject outputJson = new JsonParser().parse("{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface output = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, outputJson);
            
            InterfaceConnection connection = new InterfaceConnection(input, output);
            
            assertFalse(connection.isValid());
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testDataTypeMismatch() {
        try {
            JsonObject inputJson = new JsonParser().parse("{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface input = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, inputJson);
            JsonObject outputJson = new JsonParser().parse("{\"direction\":\"output\",\"data_type\":\"temperature\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface output = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, outputJson);
            
            InterfaceConnection connection = new InterfaceConnection(input, output);
            
            assertFalse(connection.isValid());
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
