/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelSerializer;
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
public class ConnectOperationTest {
    
    public ConnectOperationTest() {
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
     * Test of performOperation method, of class ConnectOperation.
     */
    @Test
    public void testPerformOperation() {
        try {
            JsonObject json = new JsonParser().parse("{\"action\":\"connect\",\"output\":{\"device_id\":\"dev_0\",\"interface_id\":\"int_0\"},\"input\":{\"device_id\":\"dev_1\",\"interface_id\":\"int_1\"}}").getAsJsonObject();
            ConnectOperation operation = (ConnectOperation) ModelSerializer.model(ConnectOperation.class, json);
            
            assertNotNull(operation);
            assertEquals(operation.getOutputDeviceID(), "dev_0");
            assertEquals(operation.getInputDeviceID(), "dev_1");
            assertEquals(operation.getOutputInterfaceID(), "int_0");
            assertEquals(operation.getInputInterfaceID(), "int_1");
            
        } catch (SerializationErrorException ex) {
            fail();
        }
    }
    
}
