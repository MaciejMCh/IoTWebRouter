/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Application;

import com.google.gson.JsonObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.google.gson.JsonParser;
import model.ModelSerializer;
import model.SerializableModel;
import model.SerializationErrorException;

/**
 *
 * @author maciej
 */
public class ConnectInterfacesOperationTest {
    
    public ConnectInterfacesOperationTest() {
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
     * Test of performReponsableOperation method, of class ConnectInterfacesOperation.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"request_id\":\"app_req_1\",\"action\":\"connect_interfaces\",\"connection\":{\"output_device_id\":\"dev_0\",\"output_interface_id\":\"int_0\",\"input_device_id\":\"dev_1\",\"input_interface_id\":\"int_1\"}}").getAsJsonObject();
            SerializableModel model = ModelSerializer.model(ConnectInterfacesOperation.class, json);
            
            assertNotNull(model);
            assertEquals(model.getClass(), ConnectInterfacesOperation.class);
            
            ConnectInterfacesOperation operation = (ConnectInterfacesOperation)model;
            
            assertNotNull(operation.workerOperation);
            assertEquals(operation.workerOperation.getOutputDeviceID(), "dev_0");
            assertEquals(operation.workerOperation.getOutputInterfaceID(), "int_0");
            assertEquals(operation.workerOperation.getInputDeviceID(), "dev_1");
            assertEquals(operation.workerOperation.getInputInterfaceID(), "int_1");
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
