/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.*;
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
public class ModelSerializerTest {
    
    public ModelSerializerTest() {
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
     * Test of model method, of class ModelSerializer.
     */
    @Test
    public void testStringSerialization() {
        Class clazz = ModelTestClass.class;
        JsonObject json = new JsonObject();
        json.addProperty("string_property", "string");
        SerializableModel result = ModelSerializer.model(clazz, json);
        
        assertNotNull(result);
        assertEquals(result.getClass(), clazz);
        
        ModelTestClass testModel = (ModelTestClass)result;
        
        assertEquals(testModel.getStringProperty(), "string");
    }
    
    @Test
    public void testIntSerialization() {
        Class clazz = ModelTestClass.class;
        JsonObject json = new JsonObject();
        json = new JsonParser().parse("{\"int_property\":100}").getAsJsonObject();
        SerializableModel result = ModelSerializer.model(clazz, json);
        
        assertNotNull(result);
        assertEquals(result.getClass(), clazz);
        
        ModelTestClass testModel = (ModelTestClass)result;
        assertEquals(testModel.getIntProperty(), 100);
    }
}
