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
public class OptionTest {
    
    public OptionTest() {
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
     * Test of getName method, of class Option.
     */
    @Test
    public void testInitialization() {
        Option option = new Option("name", "description", "propertyName", "o");
        assertEquals(option.getName(), "name");
        assertEquals(option.getDescription(), "description");
        assertEquals(option.getPropertyName(), "propertyName");
        assertEquals(option.getInvocation(), "o");
    }

    
}
