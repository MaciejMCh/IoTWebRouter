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
import requestOperations.Device.RegisterOperation;
import requestOperations.FakeMedium;
import requestOperations.RequestOperation;

/**
 *
 * @author maciej
 */
public class ConnectionsLogOperationTest {
    
    public ConnectionsLogOperationTest() {
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
     * Test of performOperation method, of class ConnectionsLogOperation.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"action\":\"connections\",\"possible\":true}").getAsJsonObject();
            SerializableModel model = ModelSerializer.model(ConnectionsLogOperation.class, json);
            
            assertNotNull(model);
            assertEquals(model.getClass(), ConnectionsLogOperation.class);
            
            ConnectionsLogOperation operation = (ConnectionsLogOperation) model;
            
            assertEquals(operation.possibleInterfaces, true);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testConnectionsLog() {
        try {
            JsonObject inputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"in_0\"}]}}").getAsJsonObject();
            RegisterOperation inputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, inputRegisterJson);
            FakeMedium inputRegisterMedium = new FakeMedium();
            inputRegisterOperation.medium = inputRegisterMedium;
            inputRegisterOperation.performOperation();
            String inputDeviceID = inputRegisterOperation.getRegisteringDevice().getId();
            
            JsonObject outputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"in_1\"}]}}").getAsJsonObject();
            RegisterOperation outputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, outputRegisterJson);
            FakeMedium outputRegisterMedium = new FakeMedium();
            outputRegisterOperation.medium = outputRegisterMedium;
            outputRegisterOperation.performOperation();
            String outputDeviceID = outputRegisterOperation.getRegisteringDevice().getId();
            
            JsonObject json = new JsonParser().parse("{\"action\":\"connect\",\"output_device_id\":\"" + outputDeviceID + "\",\"output_interface_id\":\"in_1\",\"input_device_id\":\"" + inputDeviceID + "\",\"input_interface_id\":\"in_0\"}").getAsJsonObject();
            ConnectOperation connectOperation = (ConnectOperation) ModelSerializer.model(ConnectOperation.class, json);
            FakeMedium connectMedium = new FakeMedium();
            connectOperation.medium = connectMedium;
            connectOperation.performOperation();
            
            JsonObject logJson = new JsonParser().parse("{\"action\":\"operation\",\"query\":\"connections\"}").getAsJsonObject();
            FakeMedium logMedium = new FakeMedium();
            RequestOperation operation = new AdminRequestOperationsSerializer().serializeOperation(logJson, logMedium);
            operation.performOperation();
            
            assertTrue(logMedium.message.contains("connections:"));
            assertTrue(logMedium.message.contains(outputDeviceID + "." + "in_1 -> " + inputDeviceID + ".in_0"));
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testPossibleConnectionsLog() {
        try {
            JsonObject inputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"in_0\"}]}}").getAsJsonObject();
            RegisterOperation inputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, inputRegisterJson);
            FakeMedium inputRegisterMedium = new FakeMedium();
            inputRegisterOperation.medium = inputRegisterMedium;
            inputRegisterOperation.performOperation();
            String inputDeviceID = inputRegisterOperation.getRegisteringDevice().getId();
            
            JsonObject outputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"in_1\"}]}}").getAsJsonObject();
            RegisterOperation outputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, outputRegisterJson);
            FakeMedium outputRegisterMedium = new FakeMedium();
            outputRegisterOperation.medium = outputRegisterMedium;
            outputRegisterOperation.performOperation();
            String outputDeviceID = outputRegisterOperation.getRegisteringDevice().getId();
            
            JsonObject logJson = new JsonParser().parse("{\"action\":\"operation\",\"query\":\"connections -p\"}").getAsJsonObject();
            FakeMedium logMedium = new FakeMedium();
            RequestOperation operation = new AdminRequestOperationsSerializer().serializeOperation(logJson, logMedium);
            operation.performOperation();
            
            assertTrue(logMedium.message.contains("connections:"));
            assertTrue(logMedium.message.contains(outputDeviceID + "." + "in_1 -> " + inputDeviceID + ".in_0"));
            
            System.out.println(logMedium.message);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
