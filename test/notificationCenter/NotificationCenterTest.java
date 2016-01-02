/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationCenter;

import com.google.gson.JsonObject;
import model.Medium;
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
public class NotificationCenterTest {
    
    public NotificationCenterTest() {
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
     * Test of getInstance method, of class NotificationCenter.
     */
    @Test
    public void testGetInstance() {
        assertNotNull(NotificationCenter.getInstance());
    }

    /**
     * Test of addMedium method, of class NotificationCenter.
     */
    @Test
    public void testAddMedium() {
        FakeMedium medium = new FakeMedium();
        NotificationCenter instance = new NotificationCenter();
        instance.addMedium(medium);
        assertTrue(instance.listeningMediums.contains(medium));
    }

    /**
     * Test of removeMedium method, of class NotificationCenter.
     */
    @Test
    public void testRemoveMedium() {
        FakeMedium medium = new FakeMedium();
        NotificationCenter instance = new NotificationCenter();
        instance.addMedium(medium);
        assertTrue(instance.listeningMediums.contains(medium));
        instance.removeMedium(medium);
        assertFalse(instance.listeningMediums.contains(medium));
    }

    /**
     * Test of notify method, of class NotificationCenter.
     */
    @Test
    public void testNotify() {
        FakeMedium medium = new FakeMedium();
        NotificationCenter instance = new NotificationCenter();
        instance.addMedium(medium);
        instance.notify(new Notification() {

            @Override
            protected String notificationType() {
                return "test";
            }

            @Override
            protected JsonObject subjectJsonRepresentation() {
                return new JsonObject();
            }
        });
        
        assertEquals(medium.message, "{\"notification_type\":\"test\",\"subject\":{}}");
    }
    
}
