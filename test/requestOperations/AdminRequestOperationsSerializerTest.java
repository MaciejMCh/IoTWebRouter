/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import requestOperations.Admin.ConnectOperation;
import requestOperations.Log.LogOperation;

/**
 *
 * @author maciej
 */
public class AdminRequestOperationsSerializerTest {
    
    public AdminRequestOperationsSerializerTest() {
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
     * Test of operationClassMap method, of class AdminRequestOperationsSerializer.
     */
    @Test
    public void testConnectSerialization() {
        JsonObject json = new JsonParser().parse("{\"action\":\"connect\",\"output\":{\"device_id\":\"dev_0\",\"interface_id\":\"in_1\"},\"input\":{\"device_id\":\"dev_1\",\"interface_id\":\"in_0\"}}").getAsJsonObject();
        FakeMedium medium = new FakeMedium();
        
        RequestOperation connectOperation = new AdminRequestOperationsSerializer().serializeOperation(json, medium);
        assertNotNull(connectOperation);
        assertEquals(connectOperation.getClass(), ConnectOperation.class);
    }
    
    @Test
    public void testLogSerialization() {
        JsonObject json = new JsonParser().parse("{\"action\" : \"log\", \"request\" : {\"action\" : \"device\", \"options\" : [], \"arguments\" : []}}").getAsJsonObject();
        FakeMedium medium = new FakeMedium();
        
        RequestOperation logOperation = new AdminRequestOperationsSerializer().serializeOperation(json, medium);
        assertNotNull(logOperation);
        assertEquals(logOperation.getClass(), LogOperation.class);
    }
    
}
