/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Device;

import requestOperations.FakeMedium;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import model.ModelSerializer;
import model.SerializationErrorException;
import model.Signal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import requestOperations.Admin.ConnectOperation;

/**
 *
 * @author maciej
 */
public class DataOperationTest {
    
    public DataOperationTest() {
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
     * Test of JSONKeyPathsByPropertyKey method, of class DataOperation.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"action\":\"data\",\"signals\":[{\"interface_id\":\"int_0\",\"message\":{\"data_type\":\"light\",\"value\":455}}]}").getAsJsonObject();
            DataOperation operation = (DataOperation) ModelSerializer.model(DataOperation.class, json);
            
            assertNotNull(operation);
            assertNotNull(operation.signals);
            assertEquals(operation.signals.size(), 1);
            
            Signal signal = operation.signals.get(0);
            
            assertEquals(signal.getSourceInterfaceID(), "int_0");
            assertEquals(signal.getMessage().getDataType(), "light");
            assertEquals(signal.getMessage().getValue(), new JsonPrimitive(455));
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testOperationPerform() {
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
            
            JsonObject dataJson = new JsonParser().parse("{\"action\":\"data\",\"signals\":[{\"interface_id\":\"in_1\",\"message\":{\"data_type\":\"light\",\"value\":455}}]}").getAsJsonObject();
            DataOperation dataOperation = (DataOperation) ModelSerializer.model(DataOperation.class, dataJson);
            
            
            assertNotNull(dataOperation);
            assertNotNull(dataOperation.signals);
            
            dataOperation.medium = outputRegisterMedium;
            
            dataOperation.performOperation();
            
            assertNull(dataOperation.getError());
            assertEquals(inputRegisterMedium.message, "{\"data_type\":\"light\",\"value\":455}");
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
