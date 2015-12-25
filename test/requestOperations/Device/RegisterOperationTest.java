/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Device;

import requestOperations.FakeMedium;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Interactor;
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
public class RegisterOperationTest {
    
    public RegisterOperationTest() {
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
     * Test of JSONKeyPathsByPropertyKey method, of class RegisterOperation.
     */
    
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"in_0\"}]}}").getAsJsonObject();
            RegisterOperation operation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, json);
            
            assertNotNull(operation);
            assertNotNull(operation.registeringDevice);
            assertEquals(operation.registeringDevice.getName(), "actuator");
            assertNotNull(operation.registeringDevice.getInterfaces());
            assertEquals(operation.registeringDevice.getInterfaces().size(), 1);
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testPerformFirstRegistration() {
        try {
            JsonObject json = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"in_0\"}]}}").getAsJsonObject();
            
            int preDevicesCount = Interactor.getInstance().getEnviroment().devices.size();
            RegisterOperation operation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, json);
            FakeMedium medium = new FakeMedium();
            operation.medium = medium;
            
            operation.performOperation();
            
            assertEquals(Interactor.getInstance().getEnviroment().devices.size() , preDevicesCount + 1);
            assertEquals(medium.message, "{\"device_id\":\""+ operation.registeringDevice.getId() +"\"}");
            assertNotNull(Interactor.getInstance().deviceForID(operation.registeringDevice.getId()));
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testPerformReconnect() {
        try {
            JsonObject firstRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"in_0\"}]}}").getAsJsonObject();
            RegisterOperation firstRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, firstRegisterJson);
            FakeMedium firstMedium = new FakeMedium();
            firstRegisterOperation.medium = firstMedium;
            firstRegisterOperation.performOperation();
            
            int preDevicesCount = Interactor.getInstance().getEnviroment().devices.size();
            String storedID = firstRegisterOperation.getRegisteringDevice().getId();
            JsonObject json = new JsonParser().parse("{\"action\":\"register\",\"stored_id\":\"" + storedID + "\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"in_0\"}]}}").getAsJsonObject();
            
            RegisterOperation operation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, json);
            FakeMedium medium = new FakeMedium();
            operation.medium = medium;
            operation.performOperation();
            
            assertEquals(Interactor.getInstance().getEnviroment().devices.size() , preDevicesCount);
            assertNull(medium.message);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    
    
}
