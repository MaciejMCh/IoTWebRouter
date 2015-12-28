/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import requestOperations.Admin.QueryJsonizer;
import com.google.gson.JsonObject;
import java.util.HashMap;
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
public class QueryJsonizerTest {
    
    public QueryJsonizerTest() {
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
     * Test of jsonizeQuery method, of class QueryJsonizer.
     */
    @Test
    public void testJsonizeQuery() {
        String query = "test arg1 arg2 -a -b";
        HashMap<String, Class> classMap = new HashMap<>();
        classMap.put("test", InterpretedTestOperation.class);
        JsonObject json = QueryJsonizer.jsonizeQuery(query, classMap);
        
        assertNotNull(json);
        
        assertTrue(json.has("action"));
        assertEquals(json.get("action").getAsString(), "test");
        
        assertTrue(json.has("firstArgument"));
        assertEquals(json.get("firstArgument").getAsString(), "arg1");
        
        assertTrue(json.has("secondArgument"));
        assertEquals(json.get("secondArgument").getAsString(), "arg2");
        
        assertFalse(json.has("thirdArgument"));
        
        assertEquals(json.get("optionA").getAsBoolean(), true);
        assertEquals(json.get("optionB").getAsBoolean(), true);
        assertEquals(json.get("optionC").getAsBoolean(), false);
    }
    
}
