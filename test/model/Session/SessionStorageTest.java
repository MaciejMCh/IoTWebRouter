/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Session;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Device;
import model.Interactor;
import model.Medium;
import model.ModelSerializer;
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
public class SessionStorageTest {
    
    public SessionStorageTest() {
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
     * Test of saveSessionPayload method, of class SessionStorage.
     */
        @Test
    public void testSaveEnviromentState() {
        try {
            LocalFileSessionStorage storage = new LocalFileSessionStorage();
            
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            Medium medium = new FakeMedium();
            
            Interactor interactor = new Interactor();
            interactor.registerDevice(device, medium);
            storage.targetInteractor = interactor;
            storage.saveSessionState();
            
            Interactor secondInteractor = new Interactor();
            storage.targetInteractor = secondInteractor;
            storage.loadSessionState();
            
            assertEquals(secondInteractor.getEnviroment().devices.size(), 1);
            
            Device loadedDevice = secondInteractor.getEnviroment().devices.get(0);
            assertEquals(loadedDevice.getId(), device.getId());
            
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testLoadEnviromentStateAndReconnect() {
        try {
            LocalFileSessionStorage storage = new LocalFileSessionStorage();
            
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            Medium medium = new FakeMedium();
            
            Interactor interactor = new Interactor();
            interactor.registerDevice(device, medium);
            storage.targetInteractor = interactor;
            storage.saveSessionState();
            
            Interactor secondInteractor = new Interactor();
            storage.targetInteractor = secondInteractor;
            storage.loadSessionState();
            
            assertEquals(secondInteractor.getEnviroment().devices.size(), 1);
            
            Device loadedDevice = secondInteractor.getEnviroment().devices.get(0);
            assertEquals(loadedDevice.getId(), device.getId());
            
            Medium newMedium = new FakeMedium();
            secondInteractor.updateMedium(newMedium, device.getId());
            assertNotNull(secondInteractor.deviceForMedium(newMedium));
            
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testLoadEnviromentStateAndRedoConnection() {
        try {
            LocalFileSessionStorage storage = new LocalFileSessionStorage();
            
            JsonObject outputJson = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_out\"}]}").getAsJsonObject();
            Device outputDevice = (Device) ModelSerializer.model(Device.class, outputJson);
            Medium outputMedium = new FakeMedium();
            
            JsonObject inputJson = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_in\"}]}").getAsJsonObject();
            Device inputDevice = (Device) ModelSerializer.model(Device.class, inputJson);
            Medium inputMedium = new FakeMedium();
            
            Interactor interactor = new Interactor();
            interactor.registerDevice(inputDevice, inputMedium);
            interactor.registerDevice(outputDevice, outputMedium);
            interactor.getRouter().connectInterfaces(outputDevice.getInterfaces().get(0), inputDevice.getInterfaces().get(0));
            
            assertEquals(interactor.getRouter().getInterfacesConnections().size(), 1);
            storage.targetInteractor = interactor;
            storage.saveSessionState();
            
            Interactor secondInteractor = new Interactor();
            storage.targetInteractor = secondInteractor;
            storage.loadSessionState();
            
            assertEquals(secondInteractor.getRouter().getInterfacesConnections().size(), 1);
            
            assertEquals(secondInteractor.getRouter().getInterfacesConnections().get(0).getOutput().getParentDevice().getId(), outputDevice.getId());
            assertEquals(secondInteractor.getRouter().getInterfacesConnections().get(0).getInput().getParentDevice().getId(), inputDevice.getId());
            
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
    
}
