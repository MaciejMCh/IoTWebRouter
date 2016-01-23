/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Session;

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
public class LocalFileSessionStorageTest {
    
    public LocalFileSessionStorageTest() {
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
     * Test of saveSessionPayload method, of class LocalFileSessionStorage.
     */
    @Test
    public void testSaveSessionPayload() {
        String payload = "payload";
        LocalFileSessionStorage instance = new LocalFileSessionStorage();
        instance.saveSessionPayload(payload);
        assertEquals(instance.loadSessionPayload(), payload);
    }
    
    @Test
    public void testSaveSessionPayloadIsNotAppending() {
        String payload = "payload";
        LocalFileSessionStorage instance = new LocalFileSessionStorage();
        instance.saveSessionPayload(payload);
        instance.saveSessionPayload(payload);
        assertEquals(instance.loadSessionPayload(), payload);
    }
    
    

    /**
     * Test of loadSessionPayload method, of class LocalFileSessionStorage.
     */
    @Test
    public void testLoadSessionPayload() {
        String payload = "payload";
        LocalFileSessionStorage instance = new LocalFileSessionStorage();
        instance.saveSessionPayload(payload);
        assertEquals(instance.loadSessionPayload(), payload);
    }
    
}
