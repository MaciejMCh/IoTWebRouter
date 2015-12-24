/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.*;
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
        try {
            Class clazz = ModelTestClass.class;
            JsonObject json = new JsonObject();
            json.addProperty("string_property", "string");
            SerializableModel result = ModelSerializer.model(clazz, json);
            
            assertNotNull(result);
            assertEquals(result.getClass(), clazz);
            
            ModelTestClass testModel = (ModelTestClass)result;
            
            assertEquals(testModel.getStringProperty(), "string");
        } catch (SerializationErrorException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void testIntSerialization() {
        try {
            Class clazz = ModelTestClass.class;
            JsonObject json = new JsonObject();
            json = new JsonParser().parse("{\"int_property\":100}").getAsJsonObject();
            SerializableModel result = ModelSerializer.model(clazz, json);
            
            assertNotNull(result);
            assertEquals(result.getClass(), clazz);
            
            ModelTestClass testModel = (ModelTestClass)result;
            assertEquals(testModel.getIntProperty(), 100);
        } catch (SerializationErrorException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void testLongSerialization() {
        try {
            Class clazz = ModelTestClass.class;
            JsonObject json = new JsonParser().parse("{\"long_property\":100}").getAsJsonObject();
            SerializableModel result = ModelSerializer.model(clazz, json);
            
            assertNotNull(result);
            assertEquals(result.getClass(), clazz);
            
            ModelTestClass testModel = (ModelTestClass)result;
            assertEquals(testModel.getLongProperty(), 100);
        } catch (SerializationErrorException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void testFloatSerialization() {
        try {
            Class clazz = ModelTestClass.class;
            JsonObject json = new JsonObject();
            json = new JsonParser().parse("{\"float_property\":100.1}").getAsJsonObject();
            SerializableModel result = ModelSerializer.model(clazz, json);
            
            assertNotNull(result);
            assertEquals(result.getClass(), clazz);
            
            ModelTestClass testModel = (ModelTestClass)result;
            assertEquals(100.1, testModel.getFloatProperty(), 0.01);
        } catch (SerializationErrorException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void testDoubleSerialization() {
        try {
            Class clazz = ModelTestClass.class;
            JsonObject json = new JsonParser().parse("{\"double_property\":100.1}").getAsJsonObject();
            SerializableModel result = ModelSerializer.model(clazz, json);
            
            assertNotNull(result);
            assertEquals(result.getClass(), clazz);
            
            ModelTestClass testModel = (ModelTestClass)result;
            assertEquals(testModel.getDoubleProperty(), 100.1, 0.1);
        } catch (SerializationErrorException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void testBooleanSerialization() {
        try {
            Class clazz = ModelTestClass.class;
            JsonObject json = new JsonParser().parse("{\"boolean_property\":true}").getAsJsonObject();
            SerializableModel result = ModelSerializer.model(clazz, json);
            
            assertNotNull(result);
            assertEquals(result.getClass(), clazz);
            
            ModelTestClass testModel = (ModelTestClass)result;
            assertEquals(testModel.getBooleanProperty(), true);
        } catch (SerializationErrorException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void testSerializableSerialization() {
        try {
            Class clazz = ModelTestClass.class;
            JsonObject json = new JsonParser().parse("{\"serializable_property\":{}}").getAsJsonObject();
            SerializableModel result = ModelSerializer.model(clazz, json);
            
            assertNotNull(result);
            assertEquals(result.getClass(), clazz);
            
            ModelTestClass testModel = (ModelTestClass)result;
            assertNotNull(testModel.getSerializableProperty());
            assertEquals(testModel.getSerializableProperty().getClass(), ModelTestClass.class);
        } catch (SerializationErrorException ex) {
            assertTrue(false);
        }
    }
    
}
