/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationCenter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
public class NotificationTest {
    
    public NotificationTest() {
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
     * Test of jsonRepresentation method, of class Notification.
     */
    @Test
    public void testJsonRepresentation() {
        Notification instance = new NotificationImpl();
        JsonObject expResult = new JsonParser().parse("{\"notification_type\":\"test_notification\",\"subject\":{\"test_string\":\"string\",\"test_bool\":true}}").getAsJsonObject();
        JsonObject result = instance.jsonRepresentation();
        assertEquals(expResult, result);
    }

    /**
     * Test of notificationType method, of class Notification.
     */
    @Test
    public void testNotificationType() {
        Notification instance = new NotificationImpl();
        String expResult = "test_notification";
        String result = instance.notificationType();
        assertEquals(expResult, result);
    }

    /**
     * Test of subjectJsonRepresentation method, of class Notification.
     */
    @Test
    public void testSubjectJsonRepresentation() {
        Notification instance = new NotificationImpl();
        JsonObject expResult = new JsonParser().parse("{\"test_string\":\"string\",\"test_bool\":true}").getAsJsonObject();
        JsonObject result = instance.subjectJsonRepresentation();
        assertEquals(expResult, result);
    }

    public class NotificationImpl extends Notification {

        public String notificationType() {
            return "test_notification";
        }

        public JsonObject subjectJsonRepresentation() {
            return new JsonParser().parse("{\"test_string\": \"string\", \"test_bool\": true}").getAsJsonObject();
        }
    }
    
}
