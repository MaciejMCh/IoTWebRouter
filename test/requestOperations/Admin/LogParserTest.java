/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Device;
import model.DeviceInterface;
import model.ModelSerializer;
import model.SerializationErrorException;
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
public class LogParserTest {
    
    public LogParserTest() {
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
     * Test of parseDevices method, of class LogParser.
     */
    @Test
    public void testParseDevice() {
        try {
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            
            assertEquals(LogParser.parseDevice(device, new LogDepth(0)), "id: "+device.getId()+" 	 name: sensor");
            assertEquals(LogParser.parseDevice(device, new LogDepth(1)), "id: "+device.getId()+" 	 name: sensor\n  	 interfaces:\n  	 id: int_li_in	 data type: light	 direction: Output");
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testParseDevices() {
        try {
            JsonObject json1 = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device1 = (Device) ModelSerializer.model(Device.class, json1);
            JsonObject json2 = new JsonParser().parse("{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_out\"}]}").getAsJsonObject();
            Device device2 = (Device) ModelSerializer.model(Device.class, json2);
            
            ArrayList<Device> devices = new ArrayList<>();
            devices.add(device1);
            devices.add(device2);
            
            assertEquals(LogParser.parseDevices(devices, new LogDepth(0)), "devices\n" + "id: "+device1.getId()+" 	 name: sensor\n" + "id: "+device2.getId()+" 	 name: actuator\n" + "" + "");
            assertTrue(LogParser.parseDevices(devices, new LogDepth(1)).contains("devices\n" + "id: dev_1 	 name: sensor\n" + "  	 interfaces:\n" + "  	 id: int_li_in	 data type: light	 direction: Output\n" + "id: dev_2 	 name: actuator\n" + "  	 interfaces:\n" + "  	 id: int_li_out	 data type: light"));
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
