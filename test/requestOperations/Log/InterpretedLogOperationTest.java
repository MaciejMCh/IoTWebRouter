/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
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
public class InterpretedLogOperationTest {
    
    public InterpretedLogOperationTest() {
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
     * Test of validateSyntax method, of class InterpretedLogOperation.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"action\" : \"log\", \"request\" : {\"action\" : \"device\", \"options\" : [\"-i\", \"-p\"], \"arguments\" : [\"dev_0\"]}}").getAsJsonObject();
            
            InterpretedLogOperation interpretedLogOperation = (InterpretedLogOperation) ModelSerializer.model(InterpretedLogOperation.class, json);
            assertNotNull(interpretedLogOperation);
            assertEquals(interpretedLogOperation.getClass(), InterpretedLogOperation.class);
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
