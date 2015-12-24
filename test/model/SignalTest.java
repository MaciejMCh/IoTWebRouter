/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        JsonObject json = new JsonParser().parse("{\"interface_id\":\"int_0\",\"message\":{\"data_type\":\"\\\"light\\\"\",\"value\":455}}").getAsJsonObject();
        try {
            ModelSerializer.model(Signal.class, json);
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
