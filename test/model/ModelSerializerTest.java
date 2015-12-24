/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.*;
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
    
    @Test
    public void testLongSerialization() {
        Class clazz = ModelTestClass.class;
        JsonObject json = new JsonParser().parse("{\"long_property\":100}").getAsJsonObject();
        SerializableModel result = ModelSerializer.model(clazz, json);
        
        assertNotNull(result);
        assertEquals(result.getClass(), clazz);
        
        ModelTestClass testModel = (ModelTestClass)result;
        assertEquals(testModel.getLongProperty(), 100);
    }
    
    @Test
    public void testFloatSerialization() {
        Class clazz = ModelTestClass.class;
        JsonObject json = new JsonObject();
        json = new JsonParser().parse("{\"float_property\":100.1}").getAsJsonObject();
        SerializableModel result = ModelSerializer.model(clazz, json);
        
        assertNotNull(result);
        assertEquals(result.getClass(), clazz);
        
        ModelTestClass testModel = (ModelTestClass)result;
        assertEquals(100.1, testModel.getFloatProperty(), 0.01);
    }
    
    @Test
    public void testDoubleSerialization() {
        Class clazz = ModelTestClass.class;
        JsonObject json = new JsonParser().parse("{\"double_property\":100.1}").getAsJsonObject();
        SerializableModel result = ModelSerializer.model(clazz, json);
        
        assertNotNull(result);
        assertEquals(result.getClass(), clazz);
        
        ModelTestClass testModel = (ModelTestClass)result;
        assertEquals(testModel.getDoubleProperty(), 100.1, 0.1);
    }
    
    @Test
    public void testBooleanSerialization() {
        Class clazz = ModelTestClass.class;
        JsonObject json = new JsonParser().parse("{\"boolean_property\":true}").getAsJsonObject();
        SerializableModel result = ModelSerializer.model(clazz, json);
        
        assertNotNull(result);
        assertEquals(result.getClass(), clazz);
        
        ModelTestClass testModel = (ModelTestClass)result;
        assertEquals(testModel.getBooleanProperty(), true);
    }
    
    @Test
    public void testSerializableSerialization() {
        Class clazz = ModelTestClass.class;
        JsonObject json = new JsonParser().parse("{\"serializable_property\":{}}").getAsJsonObject();
        SerializableModel result = ModelSerializer.model(clazz, json);
        
        assertNotNull(result);
        assertEquals(result.getClass(), clazz);
        
        ModelTestClass testModel = (ModelTestClass)result;
        assertNotNull(testModel.getSerializableProperty());
        assertEquals(testModel.getSerializableProperty().getClass(), ModelTestClass.class);
    }
    
}
