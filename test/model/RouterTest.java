/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.HashMap;
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
public class RouterTest {
    
    public RouterTest() {
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
     * Test of getRoutingTable method, of class Router.
     */
    @Test
    public void testGetRoutingTable() {
        Router router = new Router();
        assertNotNull(router.getRoutingTable());
    }

    /**
     * Test of addOutputsOfDevice method, of class Router.
     */
    @Test
    public void testAddOutputsOfDevice() {
        try {
            Router router = new Router();
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            
            router.addOutputsOfDevice(device);
            
            assertNotNull(router.getRoutingTable().get(device.getInterfaces().get(0)));
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of removeOutputsOfDevice method, of class Router.
     */
    @Test
    public void testRemoveOutputsOfDevice() {
        try {
            Router router = new Router();
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            
            router.addOutputsOfDevice(device);
            assertNotNull(router.getRoutingTable().get(device.getInterfaces().get(0)));
            
            router.removeOutputsOfDevice(device);
            assertFalse(router.getRoutingTable().containsKey(device.interfaces.get(0)));
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of getInterfacesConnections method, of class Router.
     */
    @Test
    public void testGetInterfacesConnections() {
        try {
            Router router = new Router();
            JsonObject inputJson = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device inputDevice = (Device) ModelSerializer.model(Device.class, inputJson);
            JsonObject outputJson = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device outputDevice = (Device) ModelSerializer.model(Device.class, outputJson);
            
            router.connectInterfaces(outputDevice.getInterfaces().get(0), inputDevice.getInterfaces().get(0));
            
        } catch(Exception ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of connectInterfaces method, of class Router.
     */
    @Test
    public void testConnectInterfaces() {
        System.out.println("connectInterfaces");
        DeviceInterface outputInterface = null;
        DeviceInterface inputInterface = null;
        Router instance = new Router();
        instance.connectInterfaces(outputInterface, inputInterface);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of produceRoutedSignals method, of class Router.
     */
    @Test
    public void testProduceRoutedSignals() {
        System.out.println("produceRoutedSignals");
        Signal signal = null;
        Router instance = new Router();
        ArrayList<Signal> expResult = null;
        ArrayList<Signal> result = instance.produceRoutedSignals(signal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addOuputInterface method, of class Router.
     */
    @Test
    public void testAddOuputInterface() {
        System.out.println("addOuputInterface");
        DeviceInterface outputInterface = null;
        Router instance = new Router();
        instance.addOuputInterface(outputInterface);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeOuputInterface method, of class Router.
     */
    @Test
    public void testRemoveOuputInterface() {
        System.out.println("removeOuputInterface");
        DeviceInterface outputInterface = null;
        Router instance = new Router();
        instance.removeOuputInterface(outputInterface);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
