/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
public class DeviceInterfaceTest {
    
    public DeviceInterfaceTest() {
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
     * Test of JSONKeyPathsByPropertyKey method, of class DeviceInterface.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface deviceInterface = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, json);
            
            assertNotNull(deviceInterface);
            assertEquals(deviceInterface.getDataType(), "light");
            assertEquals(deviceInterface.getId(), "int_li_in");
            assertEquals(deviceInterface.getInterfaceDirection(), DeviceInterface.InterfaceDirection.Input);
        } catch (SerializationErrorException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void testMissingID() {
        try {
            JsonObject json = new JsonParser().parse("{\"direction\":\"input\",\"data_type\":\"light\"}").getAsJsonObject();
            DeviceInterface deviceInterface = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, json);
            assertTrue(false);
        } catch (SerializationErrorException ex) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testMissingDirection() {
        try {
            JsonObject json = new JsonParser().parse("{\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface deviceInterface = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, json);
            assertTrue(false);
        } catch (SerializationErrorException ex) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testMissingDataType() {
        try {
            JsonObject json = new JsonParser().parse("{\"direction\":\"input\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface deviceInterface = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, json);
            assertTrue(false);
        } catch (SerializationErrorException ex) {
            assertTrue(true);
        }
    }
    
}
