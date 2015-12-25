/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Interactor;
import model.InterfaceConnection;
import model.ModelSerializer;
import model.SerializationErrorException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import requestOperations.Device.FakeMedium;
import requestOperations.Device.RegisterOperation;

/**
 *
 * @author maciej
 */
public class ConnectOperationTest {
    
    public ConnectOperationTest() {
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
     * Test of performOperation method, of class ConnectOperation.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"action\":\"connect\",\"output\":{\"device_id\":\"dev_0\",\"interface_id\":\"int_0\"},\"input\":{\"device_id\":\"dev_1\",\"interface_id\":\"int_1\"}}").getAsJsonObject();
            ConnectOperation operation = (ConnectOperation) ModelSerializer.model(ConnectOperation.class, json);
            
            assertNotNull(operation);
            assertEquals(operation.getOutputDeviceID(), "dev_0");
            assertEquals(operation.getInputDeviceID(), "dev_1");
            assertEquals(operation.getOutputInterfaceID(), "int_0");
            assertEquals(operation.getInputInterfaceID(), "int_1");
            
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
            
            JsonObject json = new JsonParser().parse("{\"action\":\"connect\",\"output\":{\"device_id\":\"" + outputDeviceID + "\",\"interface_id\":\"in_1\"},\"input\":{\"device_id\":\""+ inputDeviceID +"\",\"interface_id\":\"in_0\"}}").getAsJsonObject();
            ConnectOperation connectOperation = (ConnectOperation) ModelSerializer.model(ConnectOperation.class, json);
            FakeMedium connectMedium = new FakeMedium();
            connectOperation.medium = connectMedium;
            connectOperation.performOperation();
            
            InterfaceConnection interfaceConnection = Interactor.getInstance().getRouter().getInterfacesConnections().get(0);
            
            assertEquals(interfaceConnection.input, inputRegisterOperation.getRegisteringDevice().getInterfaces().get(0));
            assertEquals(interfaceConnection.output, outputRegisterOperation.getRegisteringDevice().getInterfaces().get(0));
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testOperationPerformOutputWrongDeviceID() {
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
            
            JsonObject json = new JsonParser().parse("{\"action\":\"connect\",\"output\":{\"device_id\":\"" + "wrong" + "\",\"interface_id\":\"in_1\"},\"input\":{\"device_id\":\""+ inputDeviceID +"\",\"interface_id\":\"in_0\"}}").getAsJsonObject();
            ConnectOperation connectOperation = (ConnectOperation) ModelSerializer.model(ConnectOperation.class, json);
            FakeMedium connectMedium = new FakeMedium();
            connectOperation.medium = connectMedium;
            connectOperation.performOperation();
            
            assertEquals("Device 'wrong' doesn't exist.", connectOperation.getError().getErrorMessage());
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testOperationPerformInputWrongDeviceID() {
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
            
            JsonObject json = new JsonParser().parse("{\"action\":\"connect\",\"output\":{\"device_id\":\"" + outputDeviceID + "\",\"interface_id\":\"in_1\"},\"input\":{\"device_id\":\""+ "wrong" +"\",\"interface_id\":\"in_0\"}}").getAsJsonObject();
            ConnectOperation connectOperation = (ConnectOperation) ModelSerializer.model(ConnectOperation.class, json);
            FakeMedium connectMedium = new FakeMedium();
            connectOperation.medium = connectMedium;
            connectOperation.performOperation();
            
            assertEquals("Device 'wrong' doesn't exist.", connectOperation.getError().getErrorMessage());
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testOperationPerformInputWrongInterfaceID() {
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
            
            JsonObject json = new JsonParser().parse("{\"action\":\"connect\",\"output\":{\"device_id\":\"" + outputDeviceID + "\",\"interface_id\":\"in_1\"},\"input\":{\"device_id\":\""+ inputDeviceID +"\",\"interface_id\":\"wrong\"}}").getAsJsonObject();
            ConnectOperation connectOperation = (ConnectOperation) ModelSerializer.model(ConnectOperation.class, json);
            FakeMedium connectMedium = new FakeMedium();
            connectOperation.medium = connectMedium;
            connectOperation.performOperation();
            
            assertEquals("Interface 'wrong' in device '"+ inputDeviceID +"' doesn't exist.", connectOperation.getError().getErrorMessage());
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testOperationPerformOutputWrongInterfaceID() {
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
            
            JsonObject json = new JsonParser().parse("{\"action\":\"connect\",\"output\":{\"device_id\":\"" + outputDeviceID + "\",\"interface_id\":\"wrong\"},\"input\":{\"device_id\":\""+ inputDeviceID +"\",\"interface_id\":\"in_0\"}}").getAsJsonObject();
            ConnectOperation connectOperation = (ConnectOperation) ModelSerializer.model(ConnectOperation.class, json);
            FakeMedium connectMedium = new FakeMedium();
            connectOperation.medium = connectMedium;
            connectOperation.performOperation();
            
            assertEquals("Interface 'wrong' in device '"+ outputDeviceID +"' doesn't exist.", connectOperation.getError().getErrorMessage());
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testOperationPerformInputIsOutput() {
        try {
            JsonObject inputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"in_0\"}]}}").getAsJsonObject();
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
            
            JsonObject json = new JsonParser().parse("{\"action\":\"connect\",\"output\":{\"device_id\":\"" + outputDeviceID + "\",\"interface_id\":\"in_1\"},\"input\":{\"device_id\":\""+ inputDeviceID +"\",\"interface_id\":\"in_0\"}}").getAsJsonObject();
            ConnectOperation connectOperation = (ConnectOperation) ModelSerializer.model(ConnectOperation.class, json);
            FakeMedium connectMedium = new FakeMedium();
            connectOperation.medium = connectMedium;
            connectOperation.performOperation();
            
            assertNotNull(connectOperation.getError());
            assertEquals(connectOperation.getError().getErrorMessage(), "Interface 'in_1' is 'output', but expected type is 'input'.");
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testOperationPerformOutputIsIntput() {
        try {
            JsonObject inputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"in_0\"}]}}").getAsJsonObject();
            RegisterOperation inputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, inputRegisterJson);
            FakeMedium inputRegisterMedium = new FakeMedium();
            inputRegisterOperation.medium = inputRegisterMedium;
            inputRegisterOperation.performOperation();
            String inputDeviceID = inputRegisterOperation.getRegisteringDevice().getId();
            
            JsonObject outputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"in_1\"}]}}").getAsJsonObject();
            RegisterOperation outputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, outputRegisterJson);
            FakeMedium outputRegisterMedium = new FakeMedium();
            outputRegisterOperation.medium = outputRegisterMedium;
            outputRegisterOperation.performOperation();
            String outputDeviceID = outputRegisterOperation.getRegisteringDevice().getId();
            
            JsonObject json = new JsonParser().parse("{\"action\":\"connect\",\"output\":{\"device_id\":\"" + outputDeviceID + "\",\"interface_id\":\"in_1\"},\"input\":{\"device_id\":\""+ inputDeviceID +"\",\"interface_id\":\"in_0\"}}").getAsJsonObject();
            ConnectOperation connectOperation = (ConnectOperation) ModelSerializer.model(ConnectOperation.class, json);
            FakeMedium connectMedium = new FakeMedium();
            connectOperation.medium = connectMedium;
            connectOperation.performOperation();
            
            assertNotNull(connectOperation.getError());
            assertEquals(connectOperation.getError().getErrorMessage(), "Interface 'in_1' is 'input', but expected type is 'output'.");
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
