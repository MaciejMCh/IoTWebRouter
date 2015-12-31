/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations;

import model.Medium;
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
public class ErrorOperationTest {
    
    public ErrorOperationTest() {
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
     * Test of internalServerErrorOperation method, of class ErrorOperation.
     */
    @Test
    public void testInitialization() {
        ErrorOperation errorOperation = new ErrorOperation("error", null);
        assertEquals(errorOperation.errorMessage, "error");
    }
    @Test
    public void testOperationPerform() {
        FakeMedium medium = new FakeMedium();
        ErrorOperation errorOperation = new ErrorOperation("error", medium);
        
        errorOperation.performOperation();
        assertEquals(medium.message, "error");
    }
    
}
