/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Application;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Device;
import model.DeviceInterface;
import model.InterfaceConnection;
import model.Message;
import model.ModelSerializer;
import model.SerializationErrorException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import requestOperations.Admin.LogParser;

/**
 *
 * @author maciej
 */
public class JsonParserTest {
    
    public JsonParserTest() {
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
     * Test of parseDevice method, of class JsonParser.
     */
    @Test
    public void testParseDevice() {
        try {
            JsonObject json = new com.google.gson.JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            device.changeTag("tag");
            json.addProperty("id", device.getId());
            json.addProperty("tag", "tag");
            json.addProperty("online", false);
            
            assertEquals(JsonParser.parseDevice(device), json);
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of parseDevices method, of class JsonParser.
     */
    @Test
    public void testParseDevices() {
        try {
            JsonObject json1 = new com.google.gson.JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            JsonObject json2 = new com.google.gson.JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device1 = (Device) ModelSerializer.model(Device.class, json1);
            Device device2 = (Device) ModelSerializer.model(Device.class, json2);
            ArrayList<Device> devices = new ArrayList<>();
            devices.add(device1);
            devices.add(device2);
            
            json1.addProperty("id", device1.getId());
            json1.addProperty("online", false);
            json2.addProperty("id", device2.getId());
            json2.addProperty("online", false);
            JsonObject finalJson = new JsonObject();
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(json1);
            jsonArray.add(json2);
            finalJson.add("devices", jsonArray);
            
            assertEquals(JsonParser.parseDevices(devices), finalJson);
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of parseInterface method, of class JsonParser.
     */
    @Test
    public void testParseInterface() {
        try {
            JsonObject json = new com.google.gson.JsonParser().parse("{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface deviceInterface = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, json);
            assertEquals(JsonParser.parseInterface(deviceInterface), json);
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of parseInterfaces method, of class JsonParser.
     */
    @Test
    public void testParseInterfaces() {
        try {
            JsonObject json1 = new com.google.gson.JsonParser().parse("{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
            JsonObject json2 = new com.google.gson.JsonParser().parse("{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
            DeviceInterface interface1 = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, json1);
            DeviceInterface interface2 = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, json2);
            ArrayList<DeviceInterface> interfaces = new ArrayList<>();
            interfaces.add(interface1);
            interfaces.add(interface2);
            
            JsonObject finalJson = new JsonObject();
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(json1);
            jsonArray.add(json2);
            finalJson.add("interfaces", jsonArray);
            
            assertEquals(JsonParser.parseInterfaces(interfaces), finalJson);
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of parseInterfaceConnection method, of class JsonParser.
     */
    @Test
    public void testParseInterfaceConnection() {
        try {
            JsonObject inputJson = new com.google.gson.JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device inputDevice = (Device) ModelSerializer.model(Device.class, inputJson);
            JsonObject outputJson = new com.google.gson.JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device outputDevice = (Device) ModelSerializer.model(Device.class, outputJson);
            InterfaceConnection connection = new InterfaceConnection(inputDevice.getInterfaces().get(0), outputDevice.getInterfaces().get(0));
            JsonObject finalJson = new com.google.gson.JsonParser().parse("{\"output\":{\"interface_id\":\"int_li_in\",\"device_id\":\"" + outputDevice.getId() + "\"},\"input\":{\"interface_id\":\"int_li_in\",\"device_id\":\"" + inputDevice.getId() + "\"}}").getAsJsonObject();
            
            assertEquals(JsonParser.parseInterfaceConnection(connection), finalJson);
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of parseInterfacesConnections method, of class JsonParser.
     */
    @Test
    public void testParseInterfacesConnections() {
        try {
            JsonObject inputJson = new com.google.gson.JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device inputDevice = (Device) ModelSerializer.model(Device.class, inputJson);
            JsonObject outputJson = new com.google.gson.JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device outputDevice = (Device) ModelSerializer.model(Device.class, outputJson);
            InterfaceConnection connection = new InterfaceConnection(inputDevice.getInterfaces().get(0), outputDevice.getInterfaces().get(0));
            
            JsonObject connectionJson = new com.google.gson.JsonParser().parse("{\"output\":{\"interface_id\":\"int_li_in\",\"device_id\":\"" + outputDevice.getId() + "\"},\"input\":{\"interface_id\":\"int_li_in\",\"device_id\":\"" + inputDevice.getId() + "\"}}").getAsJsonObject();
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(connectionJson);
            jsonArray.add(connectionJson);
            JsonObject finalJson = new JsonObject();
            finalJson.add("interfaces_connections", jsonArray);
            
            ArrayList<InterfaceConnection> connections = new ArrayList<>();
            connections.add(connection);
            connections.add(connection);
            
            assertEquals(JsonParser.parseInterfacesConnections(connections), finalJson);
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testParseMessage() {
        try {
            JsonObject json = new com.google.gson.JsonParser().parse("{\"data_type\":\"light\",\"value\":455}").getAsJsonObject();
            Message message = (Message) ModelSerializer.model(Message.class, json);
            
            assertEquals(json, JsonParser.parseMessage(message));
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
