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
import requestOperations.Application.ChangeDeviceTagOperation;
import requestOperations.Application.ConnectInterfacesOperation;
import requestOperations.Application.IndexConnectionsOperation;
import requestOperations.Application.IndexDevicesOperation;

/**
 *
 * @author maciej
 */
public class MobileRequestOperationsSerializerTest {
    
    public MobileRequestOperationsSerializerTest() {
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
     * Test of operationClassMap method, of class MobileRequestOperationsSerializer.
     */
    @Test
    public void testSerializeIndexDevicesOperation() {
        JsonObject json = new JsonParser().parse("{\"action\":\"index_devices\",\"request_id\":\"app_req_1\"}").getAsJsonObject();
        FakeMedium medium = new FakeMedium();
        
        RequestOperation dataOperation = new MobileRequestOperationsSerializer().serializeOperation(json, medium);
        
        assertNotNull(dataOperation);
        assertEquals(dataOperation.getClass(), IndexDevicesOperation.class);
    }
    
    @Test
    public void testSerializeIndexConnectionsOperation() {
        JsonObject json = new JsonParser().parse("{\"action\":\"index_connections\",\"request_id\":\"app_req_1\"}").getAsJsonObject();
        FakeMedium medium = new FakeMedium();
        
        RequestOperation dataOperation = new MobileRequestOperationsSerializer().serializeOperation(json, medium);
        assertNotNull(dataOperation);
        assertEquals(dataOperation.getClass(), IndexConnectionsOperation.class);
    }
    
    @Test
    public void testSerializeAddConnectionOperation() {
        JsonObject json = new JsonParser().parse("{\"request_id\":\"app_req_1\",\"action\":\"connect_interfaces\",\"connection\":{\"output_device_id\":\"dev_0\",\"output_interface_id\":\"int_0\",\"input_device_id\":\"dev_1\",\"input_interface_id\":\"int_1\"}}").getAsJsonObject();
        FakeMedium medium = new FakeMedium();
        
        RequestOperation dataOperation = new MobileRequestOperationsSerializer().serializeOperation(json, medium);
        assertNotNull(dataOperation);
        assertEquals(dataOperation.getClass(), ConnectInterfacesOperation.class);
    }
    
    @Test
    public void testSerializeChangeTagOperation() {
        JsonObject json = new JsonParser().parse("{\"action\":\"change_tag\",\"request_id\":\"app_req_1\", \"device_id\":\"dev_0\", \"tag\":\"tag\"}").getAsJsonObject();
        FakeMedium medium = new FakeMedium();
        
        RequestOperation changeOperation = new MobileRequestOperationsSerializer().serializeOperation(json, medium);
        assertNotNull(changeOperation);
        assertEquals(changeOperation.getClass(), ChangeDeviceTagOperation.class);
    }
    
}
