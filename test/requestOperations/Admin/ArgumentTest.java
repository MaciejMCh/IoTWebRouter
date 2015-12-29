/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

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
public class ArgumentTest {
    
    public ArgumentTest() {
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
     * Test of getArgumentName method, of class Argument.
     */
    @Test
    public void testInitialization() {
        Argument argument = new Argument("argument_name", "description", "propertyName", true);
        assertEquals(argument.getArgumentName(), "argument_name");
        assertEquals(argument.getDescription(), "description");
        assertEquals(argument.getPropertyName(), "propertyName");
        assertTrue(argument.isRequired);
    }
    
}
