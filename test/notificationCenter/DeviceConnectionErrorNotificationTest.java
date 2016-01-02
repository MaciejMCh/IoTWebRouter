/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationCenter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Device;
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
public class DeviceConnectionErrorNotificationTest {
    
    public DeviceConnectionErrorNotificationTest() {
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
     * Test of notificationType method, of class DeviceConnectionErrorNotification.
     */
    @Test
    public void testInitialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            DeviceConnectionErrorNotification instance = new DeviceConnectionErrorNotification(device);
            System.out.println(instance.jsonRepresentation());
            JsonObject expResult = new JsonParser().parse("{\"notification_type\":\"device_connection_error\",\"subject\":{\"id\":\"" + device.getId() + "\",\"name\":\"sensor\",\"interfaces\":[{\"id\":\"int_li_in\",\"data_type\":\"light\",\"direction\":\"output\"}]}}").getAsJsonObject();
            JsonObject result = instance.jsonRepresentation();
            assertEquals(expResult, result);
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
