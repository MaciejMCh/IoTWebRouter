/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Session;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class RemoteSQLSessionStorageTest {
    
    public RemoteSQLSessionStorageTest() {
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
     * Test of saveSessionPayload method, of class RemoteSQLSessionStorage.
     */
    @Test
    public void testSaveSessionPayload() {
        try {
            RemoteSQLSessionStorage storage = new RemoteSQLSessionStorage();
            Connection connection = storage.getConnection();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } 
    }
    
}
