/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import com.google.gson.JsonObject;
import com.sun.corba.se.impl.orbutil.ORBUtility;
import java.util.ArrayList;
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
public class InterpretedOperationTest {
    
    public InterpretedOperationTest() {
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
     * Test of arguments method, of class InterpretedOperation.
     */
    @Test
    public void testPropertyMapping() {
        InterpretedTestOperation operation = new InterpretedTestOperation();
        HashMap<String, String> map = operation.JSONKeyPathsByPropertyKey();
        
        assertTrue(map.containsKey("firstArgument"));
        assertTrue(map.containsKey("secondArgument"));
        assertTrue(map.containsKey("thirdArgument"));
        assertEquals(map.get("firstArgument"), "first_argument");
        assertEquals(map.get("secondArgument"), "second_argument");
        assertEquals(map.get("thirdArgument"), "third_argument");
    }
    
}
