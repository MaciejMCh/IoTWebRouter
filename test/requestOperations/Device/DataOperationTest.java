/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Device;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.ModelSerializer;
import model.SerializationErrorException;
import model.Signal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author maciej
 */
public class DataOperationTest {
    
    public DataOperationTest() {
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
     * Test of JSONKeyPathsByPropertyKey method, of class DataOperation.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"action\":\"data\",\"signals\":[{\"interface_id\":\"int_0\",\"message\":{\"data_type\":\"light\",\"value\":455}}]}").getAsJsonObject();
            DataOperation operation = (DataOperation) ModelSerializer.model(DataOperation.class, json);
            
            assertNotNull(operation);
            assertNotNull(operation.signals);
            assertEquals(operation.signals.size(), 1);
            
            Signal signal = operation.signals.get(0);
            
            assertEquals(signal.getSourceInterfaceID(), "int_0");
            assertEquals(signal.getMessage().getDataType(), "light");
            assertEquals(signal.getMessage().getValue(), "455");
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
