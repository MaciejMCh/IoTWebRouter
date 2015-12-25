/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LogOperationTest {
    
    public LogOperationTest() {
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
     * Test of mapJson method, of class LogOperation.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"action\" : \"log\", \"request\" : {\"action\" : \"device\", \"options\" : [], \"arguments\" : []}}").getAsJsonObject();
            LogOperation logOperation = (LogOperation) ModelSerializer.model(LogOperation.class, json);
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
        
    }
    
}
