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

/**
 *
 * @author maciej
 */
public class RequestResponseTest {
    
    public RequestResponseTest() {
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
     * Test of successResponse method, of class RequestResponse.
     */
    @Test
    public void testSuccessResponse() {
        JsonObject json = new JsonObject();
        json.addProperty("model", "model");
        RequestResponse successResponse = RequestResponse.successResponse("req_1", json);
        
        assertEquals(successResponse.requestID, "req_1");
        assertEquals(successResponse.response, json);
        assertEquals(successResponse.result, "success");
    }

    /**
     * Test of errorResponse method, of class RequestResponse.
     */
    @Test
    public void testErrorResponse() {
        JsonObject json = new JsonObject();
        json.addProperty("message", "failure");
        RequestResponse successResponse = RequestResponse.errorResponse("req_1", "failure");
        
        assertEquals(successResponse.requestID, "req_1");
        assertEquals(successResponse.response, json);
        assertEquals(successResponse.result, "error");
    }

    /**
     * Test of jsonRepresentation method, of class RequestResponse.
     */
    @Test
    public void testJsonRepresentation() {
        RequestResponse instance = null;
        JsonObject expResult = null;
        JsonObject result = instance.jsonRepresentation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
