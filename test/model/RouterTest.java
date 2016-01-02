/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
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
            
            router.addOutputsOfDevice(outputDevice);
            router.connectInterfaces(outputDevice.getInterfaces().get(0), inputDevice.getInterfaces().get(0));
            
            assertEquals(router.getInterfacesConnections().size(), 1);
            
            InterfaceConnection connection = router.getInterfacesConnections().get(0);
            assertEquals(connection.input, inputDevice.interfaces.get(0));
            assertEquals(connection.output, outputDevice.interfaces.get(0));
            
        } catch(Exception ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testGetInterfacesConnectionsOutputsNotAdded() {
        try {
            Router router = new Router();
            JsonObject inputJson = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device inputDevice = (Device) ModelSerializer.model(Device.class, inputJson);
            JsonObject outputJson = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device outputDevice = (Device) ModelSerializer.model(Device.class, outputJson);
           
            router.connectInterfaces(outputDevice.getInterfaces().get(0), inputDevice.getInterfaces().get(0));
            
            assertEquals(router.getInterfacesConnections().size(), 0);
            
        } catch(Exception ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of connectInterfaces method, of class Router.
     */
    @Test
    public void testConnectInterfaces() {
        try {
            Router router = new Router();
            JsonObject inputJson = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device inputDevice = (Device) ModelSerializer.model(Device.class, inputJson);
            JsonObject outputJson = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device outputDevice = (Device) ModelSerializer.model(Device.class, outputJson);
            
            router.addOutputsOfDevice(outputDevice);
            router.connectInterfaces(outputDevice.getInterfaces().get(0), inputDevice.getInterfaces().get(0));
            
            assertEquals(router.getRoutingTable().get(outputDevice.getInterfaces().get(0)).get(0), inputDevice.getInterfaces().get(0));
            
        } catch(Exception ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of produceRoutedSignals method, of class Router.
     */
    @Test
    public void testProduceRoutedSignals() {
        try {
            Router router = new Router();
            JsonObject inputJson = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device inputDevice = (Device) ModelSerializer.model(Device.class, inputJson);
            JsonObject outputJson = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device outputDevice = (Device) ModelSerializer.model(Device.class, outputJson);
            
            router.addOutputsOfDevice(outputDevice);
            router.connectInterfaces(outputDevice.getInterfaces().get(0), inputDevice.getInterfaces().get(0));
            
            JsonObject json = new JsonParser().parse("{\"interface_id\":\"int_0\",\"message\":{\"data_type\":\"light\",\"value\":455}}").getAsJsonObject();
            Signal signal = (Signal) ModelSerializer.model(Signal.class, json);
            signal.sourceInterface = outputDevice.getInterfaces().get(0);
            
            ArrayList<Signal> signals = router.produceRoutedSignals(signal);
            
            assertEquals(1, signals.size());
            
            Signal producedSignal = signals.get(0);
            
            assertEquals(producedSignal.getMessage(), signal.getMessage());
            assertEquals(producedSignal.getSourceDeviceInterface(), signal.getSourceDeviceInterface());
            assertNotNull(producedSignal.getDestinationInterface());
            assertEquals(producedSignal.getDestinationInterface(), inputDevice.getInterfaces().get(0));
            
        } catch(Exception ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of addOuputInterface method, of class Router.
     */
    @Test
    public void testAddOuputInterface() {
        try {
            Router router = new Router();
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            
            DeviceInterface outputInterface = device.getInterfaces().get(0);
            router.addOuputInterface(outputInterface);
            
            assertNotNull(router.getRoutingTable().get(outputInterface));
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of removeOuputInterface method, of class Router.
     */
    @Test
    public void testRemoveOuputInterface() {
        try {
            Router router = new Router();
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            
            DeviceInterface outputInterface = device.getInterfaces().get(0);
            router.addOuputInterface(outputInterface);
            
            assertNotNull(router.getRoutingTable().get(outputInterface));
            
            router.removeOuputInterface(outputInterface);
            
            assertFalse(router.getRoutingTable().containsKey(outputInterface));
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
