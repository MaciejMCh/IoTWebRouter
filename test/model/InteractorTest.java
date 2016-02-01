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
import requestOperations.Device.RegisterOperation;
import requestOperations.FakeMedium;

/**
 *
 * @author maciej
 */
public class InteractorTest {
    
    public InteractorTest() {
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
     * Test of getInstance method, of class Interactor.
     */
    @Test
    public void testGetInstance() {
        assertNotNull(Interactor.getInstance());
    }

    /**
     * Test of getRouter method, of class Interactor.
     */
    @Test
    public void testGetRouter() {
        Interactor instance = new Interactor();
        assertNotNull(instance.getRouter());
    }

    /**
     * Test of getEnviroment method, of class Interactor.
     */
    @Test
    public void testGetEnviroment() {
        Interactor instance = new Interactor();
        assertNotNull(instance.getEnviroment());
    }

    /**
     * Test of registerDevice method, of class Interactor.
     */
    @Test
    public void testRegisterDevice() {
        Device device = new Device();
        Medium medium = new FakeMedium();
        Interactor instance = new Interactor();
        instance.registerDevice(device, medium);
        
        assertEquals(instance.getEnviroment().devices.get(0), device);
        assertEquals(instance.deviceForMedium(medium), device);
        assertEquals(instance.deviceMediumMap.get(device), medium);
    }

    /**
     * Test of updateMedium method, of class Interactor.
     */
    @Test
    public void testUpdateMedium() {
        try {
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            Medium medium = new FakeMedium();
            Interactor instance = new Interactor();
            instance.registerDevice(device, medium);
            
            assertEquals(instance.mediumDeviceMap.get(medium), device);
            
            Medium newMedium = new FakeMedium();
            
            instance.updateMedium(newMedium, device.getId());
            
            assertEquals(instance.mediumDeviceMap.get(newMedium), device);
            assertFalse(instance.mediumDeviceMap.containsKey(medium));
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of mediumClosed method, of class Interactor.
     */
    @Test
    public void testMediumClosed() {
        Device device = new Device();
        Medium medium = new FakeMedium();
        Interactor instance = new Interactor();
        instance.registerDevice(device, medium);
        
        assertEquals(instance.getEnviroment().devices.get(0), device);
        assertEquals(instance.mediumDeviceMap.get(medium), device);
        assertEquals(instance.deviceMediumMap.get(device), medium);
        
        instance.mediumClosed(medium);
        
        assertEquals(instance.getEnviroment().devices.get(0), device);
        assertFalse(instance.mediumDeviceMap.containsKey(medium));
        assertFalse(instance.mediumDeviceMap.containsValue(device));
        assertFalse(instance.deviceMediumMap.containsKey(device));
        assertFalse(instance.deviceMediumMap.containsValue(medium));
    }

    /**
     * Test of deviceForMedium method, of class Interactor.
     */
    @Test
    public void testDeviceForMedium() {
        Device device = new Device();
        Medium medium = new FakeMedium();
        Interactor instance = new Interactor();
        instance.registerDevice(device, medium);
        
        assertEquals(instance.deviceForMedium(medium), device);
    }

    /**
     * Test of sessionOfDevice method, of class Interactor.
     */
    @Test
    public void testMediumOfDevice() {
        Device device = new Device();
        Medium medium = new FakeMedium();
        Interactor instance = new Interactor();
        instance.registerDevice(device, medium);
        
        assertEquals(instance.mediumOfDevice(device), medium);
    }

    /**
     * Test of deviceForID method, of class Interactor.
     */
    @Test
    public void testDeviceForID() {
        try {
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            Medium medium = new FakeMedium();
            Interactor instance = new Interactor();
            instance.registerDevice(device, medium);
            
            assertEquals(instance.deviceForID(device.getId()), device);
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testRemoveDuplicates() {
        try {
            Interactor.getInstance().restart();
            
            JsonObject json = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"in_0\"}]}}").getAsJsonObject();
            RegisterOperation operation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, json);
            operation.medium = new FakeMedium();
            operation.performOperation();
            
            Device firstRegistrationDevice = operation.getRegisteringDevice();
            
            assertTrue(Interactor.getInstance().getEnviroment().devices.contains(firstRegistrationDevice));
            assertEquals(Interactor.getInstance().getEnviroment().devices.size(), 1);
            
            Interactor.getInstance().mediumClosed(operation.medium);
            
            assertTrue(Interactor.getInstance().getEnviroment().devices.contains(firstRegistrationDevice));
            assertEquals(Interactor.getInstance().getEnviroment().devices.size(), 1);
            
            RegisterOperation secondOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, json);
            secondOperation.medium = new FakeMedium();
            secondOperation.performOperation();
            
            assertFalse(Interactor.getInstance().getEnviroment().devices.contains(firstRegistrationDevice));
            assertTrue(Interactor.getInstance().getEnviroment().devices.contains(secondOperation.getRegisteringDevice()));
            assertEquals(Interactor.getInstance().getEnviroment().devices.size(), 1);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    
}
