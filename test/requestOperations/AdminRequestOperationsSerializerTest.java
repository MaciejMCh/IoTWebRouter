/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import requestOperations.Admin.ConnectOperation;
import requestOperations.Admin.ConnectionsLogOperation;
import requestOperations.Admin.DeviceLogOperation;

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
    public void testDevicesLogSerialization() {
        JsonObject json = new JsonParser().parse("{\"action\":\"operation\",\"query\":\"devices\"}").getAsJsonObject();
        FakeMedium medium = new FakeMedium();
        
        RequestOperation connectOperation = new AdminRequestOperationsSerializer().serializeOperation(json, medium);
        
        assertNotNull(connectOperation);
        assertEquals(connectOperation.getClass(), DeviceLogOperation.class);
    }
    
    @Test
    public void testConnecitonsLogSerialization() {
        JsonObject json = new JsonParser().parse("{\"action\":\"operation\",\"query\":\"connections\"}").getAsJsonObject();
        FakeMedium medium = new FakeMedium();
        
        RequestOperation connectOperation = new AdminRequestOperationsSerializer().serializeOperation(json, medium);
        
        assertNotNull(connectOperation);
        assertEquals(connectOperation.getClass(), ConnectionsLogOperation.class);
    }
    
    @Test
    public void testConnectSerialization() {
        JsonObject json = new JsonParser().parse("{\"action\":\"operation\",\"query\":\"connect dev_0 int_0 dev_1 int_1\"}").getAsJsonObject();
        FakeMedium medium = new FakeMedium();
        
        RequestOperation connectOperation = new AdminRequestOperationsSerializer().serializeOperation(json, medium);
        
        assertNotNull(connectOperation);
        assertEquals(connectOperation.getClass(), ConnectOperation.class);
    }
    
}
