/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Application;

import com.google.gson.JsonObject;
import model.ModelSerializer;
import model.SerializableModel;
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
public class IndexDevicesOperationTest {
    
    public IndexDevicesOperationTest() {
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
     * Test of performReponsableOperation method, of class IndexDevicesOperation.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new com.google.gson.JsonParser().parse("{\"action\":\"index_devices\",\"request_id\":\"app_req_1\"}").getAsJsonObject();
            SerializableModel model = ModelSerializer.model(IndexDevicesOperation.class, json);
            assertNotNull(model);
            assertEquals(model.getClass(), IndexDevicesOperation.class);
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
