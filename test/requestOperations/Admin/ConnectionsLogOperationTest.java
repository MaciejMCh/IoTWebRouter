/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
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
public class ConnectionsLogOperationTest {
    
    public ConnectionsLogOperationTest() {
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
     * Test of performOperation method, of class ConnectionsLogOperation.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"action\":\"connections\",\"possible\":true}").getAsJsonObject();
            SerializableModel model = ModelSerializer.model(ConnectionsLogOperation.class, json);
            
            assertNotNull(model);
            assertEquals(model.getClass(), ConnectionsLogOperation.class);
            
            ConnectionsLogOperation operation = (ConnectionsLogOperation) model;
            
            assertEquals(operation.possibleInterfaces, true);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
