/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class EnviromentTest {
    
    public EnviromentTest() {
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
     * Test of addDevice method, of class Enviroment.
     */
    @Test
    public void testAddDevice() {
        Device device = new Device();
        Enviroment instance = new Enviroment();
        instance.addDevice(device);
        
        assertEquals(instance.devices.size(), 1);
        assertEquals(instance.devices.get(0), device);
    }

    /**
     * Test of removeDevice method, of class Enviroment.
     */
    @Test
    public void testRemoveDevice() {
        Device device = new Device();
        Enviroment instance = new Enviroment();
        instance.addDevice(device);
        
        assertEquals(instance.devices.size(), 1);
        assertEquals(instance.devices.get(0), device);
        
        instance.removeDevice(device);
        assertEquals(instance.devices.size(), 0);
    }
    
}
