/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class SignalTest {
    
    public SignalTest() {
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
     * Test of JSONKeyPathsByPropertyKey method, of class Signal.
     */
    @Test
    public void testSerialization() {
        JsonObject json = new JsonParser().parse("{\"interface_id\":\"int_0\",\"message\":{\"data_type\":\"light\",\"value\":455}}").getAsJsonObject();
        try {
            Signal signal = (Signal) ModelSerializer.model(Signal.class, json);
            assertNotNull(signal);
            assertEquals(signal.getSourceInterfaceID(), "int_0");
            assertNotNull(signal.getMessage());
            assertEquals(signal.getMessage().getDataType(), "light");
            assertEquals(signal.getMessage().getValue(), "455");
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
