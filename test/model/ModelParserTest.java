///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package model;
//
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import java.util.ArrayList;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author maciej
// */
//public class ModelParserTest {
//    
//    public ModelParserTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of json method, of class ModelParser.
//     */
//    @Test
//    public void testStringParsing() {
//        ModelTestClass testModel = new ModelTestClass();
//        JsonObject json = ModelParser.json(testModel);
//        
//        assertNotNull(json);
//        
//        testModel.stringProperty = "string";
//        json = ModelParser.json(testModel);
//        assertTrue(json.has("string_property"));
//        assertEquals(json.get("string_property").getAsString(), "string");
//    }
//    
//    @Test
//    public void testIntParsing() {
//        ModelTestClass testModel = new ModelTestClass();
//        JsonObject json = ModelParser.json(testModel);
//        
//        assertNotNull(json);
//        
//        testModel.intProperty = 200;
//        json = ModelParser.json(testModel);
//        assertTrue(json.has("int_property"));
//        assertEquals(json.get("int_property").getAsString(), "200");
//    }
//    
//    @Test
//    public void testSerializableParsing() {
//        ModelTestClass testModel = new ModelTestClass();
//        JsonObject json = ModelParser.json(testModel);
//        
//        assertNotNull(json);
//        
//        testModel.serializableProperty = new ModelTestClass();
//        json = ModelParser.json(testModel);
//        assertTrue(json.has("serializable_property"));
//        assertTrue(json.get("serializable_property").getAsString().startsWith("{"));
//        assertTrue(json.get("serializable_property").getAsString().endsWith("}"));
//    }
//    
//    @Test
//    public void testEnumParsing() {
//        try {
//            JsonObject inJson = new JsonParser().parse("{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_li_in\"}").getAsJsonObject();
//            DeviceInterface deviceInterface = (DeviceInterface) ModelSerializer.model(DeviceInterface.class, inJson);
//            JsonObject json = ModelParser.json(deviceInterface);
//            
//            assertNotNull(json);
//            assertTrue(json.has("direction"));
//            assertEquals(json.get("direction").getAsString(), "input");
//        } catch(Exception e) {
//            fail(e.toString());
//        }
//    }
//    
//    @Test
//    public void testArrayListParsing() {
//        ModelTestClass testModel = new ModelTestClass();
//        JsonObject json = ModelParser.json(testModel);
//        
//        assertNotNull(json);
//        
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("one");
//        arrayList.add("two");
//        arrayList.add("three");
//        
//        testModel.arrayProperty = arrayList;
//        json = ModelParser.json(testModel);
//        assertTrue(json.has("array_property"));
//        
//        
//    }
//    
//}
