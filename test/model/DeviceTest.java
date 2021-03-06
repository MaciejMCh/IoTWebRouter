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
public class DeviceTest {
    
    public DeviceTest() {
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
     * Test of getId method, of class Device.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            
            assertNotNull(device);
            assertEquals(device.getName(), "sensor");
            
            assertNotNull(device.getInterfaces());
            assertEquals(device.getInterfaces().size(), 1);
            
            DeviceInterface deviceInterface = device.getInterfaces().get(0);
            
            assertEquals(deviceInterface.getDataType(), "light");
            assertEquals(deviceInterface.getId(), "int_li_in");
            assertEquals(deviceInterface.getInterfaceDirection(), DeviceInterface.InterfaceDirection.Output);
        } catch (SerializationErrorException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void testParent() {
        try {
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            
            assertNotNull(device);
            
            assertNotNull(device.getInterfaces());
            assertEquals(device.getInterfaces().size(), 1);
            
            DeviceInterface deviceInterface = device.getInterfaces().get(0);
            
            assertEquals(deviceInterface.getParentDevice(), device);
        } catch (SerializationErrorException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void testMissingInterface() {
        JsonObject json = new JsonParser().parse("{\"name\":\"sensor\"}").getAsJsonObject();
        try {
            Device device = (Device) ModelSerializer.model(Device.class, json);
            assertTrue(false);
        } catch(SerializationErrorException e) {
            assertTrue(true);
        }
        
    }
    @Test
    public void testAssignTag() {
        Device device = new Device();
        device.changeTag("tag");
        assertEquals(device.getTag(), "tag");
    }
    
    @Test
    public void testDeviceIsDuplicate() {
        try {
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device1 = (Device) ModelSerializer.model(Device.class, json);
            Device device2 = (Device) ModelSerializer.model(Device.class, json);
            assertTrue(device1.isDuplicate(device2));
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testDeviceIsDuplicateDiffrentNames() {
        try {
            JsonObject json1 = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            JsonObject json2 = new JsonParser().parse("{\"name\":\"sensor2\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device1 = (Device) ModelSerializer.model(Device.class, json1);
            Device device2 = (Device) ModelSerializer.model(Device.class, json2);
            assertFalse(device1.isDuplicate(device2));
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testDeviceIsDuplicateDiffrentInterfacesCount() {
        try {
            JsonObject json1 = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            JsonObject json2 = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}, {\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device1 = (Device) ModelSerializer.model(Device.class, json1);
            Device device2 = (Device) ModelSerializer.model(Device.class, json2);
            assertFalse(device1.isDuplicate(device2));
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testDeviceIsDuplicateDiffrentInterfaceID() {
        try {
            JsonObject json1 = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            JsonObject json2 = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"diffrent\"}]}").getAsJsonObject();
            Device device1 = (Device) ModelSerializer.model(Device.class, json1);
            Device device2 = (Device) ModelSerializer.model(Device.class, json2);
            assertFalse(device1.isDuplicate(device2));
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testDeviceIsDuplicateDiffrentInterfaceDataType() {
        try {
            JsonObject json1 = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            JsonObject json2 = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"temperature\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device1 = (Device) ModelSerializer.model(Device.class, json1);
            Device device2 = (Device) ModelSerializer.model(Device.class, json2);
            assertFalse(device1.isDuplicate(device2));
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testDeviceIsDuplicateDiffrentInterfaceDirection() {
        try {
            JsonObject json1 = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            JsonObject json2 = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device1 = (Device) ModelSerializer.model(Device.class, json1);
            Device device2 = (Device) ModelSerializer.model(Device.class, json2);
            assertFalse(device1.isDuplicate(device2));
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
}
