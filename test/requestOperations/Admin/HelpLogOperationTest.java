/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
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
import requestOperations.AdminRequestOperationsSerializer;
import requestOperations.FakeMedium;
import requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public class HelpLogOperationTest {
    
    public HelpLogOperationTest() {
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
     * Test of performOperation method, of class HelpLogOperation.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"action\":\"help\",\"syntax\":true, \"details\":false}").getAsJsonObject();
            SerializableModel model = ModelSerializer.model(HelpLogOperation.class, json);
            
            assertNotNull(model);
            assertEquals(model.getClass(), HelpLogOperation.class);
            
            HelpLogOperation operation = (HelpLogOperation) model;
            
            assertTrue(operation.syntax);
            assertFalse(operation.details);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testLogHelp() {
        JsonObject logJson = new JsonParser().parse("{\"action\":\"operation\",\"query\":\"help\"}").getAsJsonObject();
        FakeMedium logMedium = new FakeMedium();
        RequestOperation operation = new AdminRequestOperationsSerializer().serializeOperation(logJson, logMedium);
        operation.performOperation();
        
        assertTrue(logMedium.message.contains("operations:"));
    }
    
    @Test
    public void testLogHelpAndSyntax() {
        JsonObject logJson = new JsonParser().parse("{\"action\":\"operation\",\"query\":\"help -s\"}").getAsJsonObject();
        FakeMedium logMedium = new FakeMedium();
        RequestOperation operation = new AdminRequestOperationsSerializer().serializeOperation(logJson, logMedium);
        operation.performOperation();
        
        assertTrue(logMedium.message.contains("operations:"));
        assertTrue(logMedium.message.contains("syntax:"));
    }
    
    @Test
    public void testLogHelpAndDetails() {
        JsonObject logJson = new JsonParser().parse("{\"action\":\"operation\",\"query\":\"help -d\"}").getAsJsonObject();
        FakeMedium logMedium = new FakeMedium();
        RequestOperation operation = new AdminRequestOperationsSerializer().serializeOperation(logJson, logMedium);
        operation.performOperation();
        
        assertTrue(logMedium.message.contains("operations:"));
        assertTrue(logMedium.message.contains("syntax:"));
        assertTrue(logMedium.message.contains("arguments:"));
        assertTrue(logMedium.message.contains("options:"));
    }
    
}
