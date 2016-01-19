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
    public void testSaveEnviromentState() {
        try {
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            Medium medium = new FakeMedium();
            
            Interactor interactor = new Interactor();
            interactor.registerDevice(device, medium);
            interactor.saveEnviromentState();
            
            Interactor secondInteractor = new Interactor();
            secondInteractor.loadEnviromentState();
            
            assertEquals(secondInteractor.enviroment.devices.size(), 1);
            
            Device loadedDevice = secondInteractor.enviroment.devices.get(0);
            assertEquals(loadedDevice.getId(), device.getId());
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testLoadEnviromentStateAndReconnect() {
        try {
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            Medium medium = new FakeMedium();
            
            Interactor interactor = new Interactor();
            interactor.registerDevice(device, medium);
            interactor.saveEnviromentState();
            
            Interactor secondInteractor = new Interactor();
            secondInteractor.loadEnviromentState();
            
            assertEquals(secondInteractor.enviroment.devices.size(), 1);
            
            Device loadedDevice = secondInteractor.enviroment.devices.get(0);
            assertEquals(loadedDevice.getId(), device.getId());
            
            Medium newMedium = new FakeMedium();
            secondInteractor.updateMedium(newMedium, device.getId());
            assertNotNull(secondInteractor.deviceForMedium(newMedium));
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testLoadEnviromentStateAndRedoConnection() {
        try {
            JsonObject outputJson = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_out\"}]}").getAsJsonObject();
            Device outputDevice = (Device) ModelSerializer.model(Device.class, outputJson);
            Medium outputMedium = new FakeMedium();
            
            JsonObject inputJson = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_in\"}]}").getAsJsonObject();
            Device inputDevice = (Device) ModelSerializer.model(Device.class, inputJson);
            Medium inputMedium = new FakeMedium();
            
            Interactor interactor = new Interactor();
            interactor.registerDevice(inputDevice, inputMedium);
            interactor.registerDevice(outputDevice, outputMedium);
            interactor.getRouter().connectInterfaces(outputDevice.interfaces.get(0), inputDevice.interfaces.get(0));
            
            assertEquals(interactor.getRouter().getInterfacesConnections().size(), 1);
            interactor.saveEnviromentState();
            
            Interactor secondInteractor = new Interactor();
            secondInteractor.loadEnviromentState();
            
            assertEquals(secondInteractor.getRouter().getInterfacesConnections().size(), 1);
            
            assertEquals(secondInteractor.getRouter().getInterfacesConnections().get(0).output.getParentDevice().getId(), outputDevice.getId());
            assertEquals(secondInteractor.getRouter().getInterfacesConnections().get(0).input.getParentDevice().getId(), inputDevice.getId());
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
