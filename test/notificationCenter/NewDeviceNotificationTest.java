/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationCenter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
public class NewDeviceNotificationTest {
    
    public NewDeviceNotificationTest() {
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
     * Test of notificationType method, of class NewDeviceNotification.
     */
    @Test
    public void testNotificationType() {
        NewDeviceNotification instance = new NewDeviceNotification(null);
        String expResult = "new_device";
        String result = instance.notificationType();
        assertEquals(expResult, result);
    }

    /**
     * Test of subjectJsonRepresentation method, of class NewDeviceNotification.
     */
    @Test
    public void testSubjectJsonRepresentation() {
        try {
            JsonObject json = new JsonParser().parse("{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_li_in\"}]}").getAsJsonObject();
            Device device = (Device) ModelSerializer.model(Device.class, json);
            DeviceReconnectNotification instance = new DeviceReconnectNotification(device);
            JsonObject expResult = new JsonParser().parse("{\"id\":\"" + device.getId() + "\",\"name\":\"sensor\",\"interfaces\":[{\"id\":\"int_li_in\",\"data_type\":\"light\",\"direction\":\"output\"}]}").getAsJsonObject();
            JsonObject result = instance.subjectJsonRepresentation();
            assertEquals(expResult, result);
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
