/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
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
public class DeviceInterfaceTest {
    
    public DeviceInterfaceTest() {
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
     * Test of JSONKeyPathsByPropertyKey method, of class DeviceInterface.
     */
    @Test
    public void testSerialization() {
        JsonObject json = new JsonParser().parse("{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
        DeviceInterface deviceInterface = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, json);
//        
//        assertNotNull(device);
//        assertEquals(device.getName(), "sensor");
//        
//        assertNotNull(device.getInterfaces());
//        assertEquals(device.getInterfaces().size(), 1);
    }
    
}
