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
import requestOperations.Device.RegisterOperation;
import requestOperations.FakeMedium;

/**
 *
 * @author maciej
 */
public class DisconnectOperationTest {
    
    public DisconnectOperationTest() {
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
     * Test of performOperation method, of class DisconnectOperation.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new JsonParser().parse("{\"action\":\"connect\",\"output_device_id\":\"dev_0\",\"output_interface_id\":\"int_0\",\"input_device_id\":\"dev_1\",\"input_interface_id\":\"int_1\"}").getAsJsonObject();
            ConnectOperation operation = (ConnectOperation) ModelSerializer.model(ConnectOperation.class, json);
            
            assertNotNull(operation);
            assertNotNull(operation.getOutputDeviceID());
            assertNotNull(operation.getInputDeviceID());
            assertNotNull(operation.getOutputInterfaceID());
            assertNotNull(operation.getInputInterfaceID());
            
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
            Interactor.getInstance().restart();
                    
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
            
            int connectionsCount = Interactor.getInstance().getRouter().getInterfacesConnections().size();
            
            assertEquals(Interactor.getInstance().getRouter().getInterfacesConnections().size(), 0);
            
            connectOperation.performOperation();
            
            assertEquals(Interactor.getInstance().getRouter().getInterfacesConnections().size(), 1);
            
            JsonObject disconnectJson = new JsonParser().parse("{\"action\":\"disconnect\",\"output_device_id\":\"" + outputDeviceID + "\",\"output_interface_id\":\"in_1\",\"input_device_id\":\"" + inputDeviceID + "\",\"input_interface_id\":\"in_0\"}").getAsJsonObject();
            DisconnectOperation disconnectOperation = (DisconnectOperation) ModelSerializer.model(DisconnectOperation.class, disconnectJson);
            
            assertEquals(disconnectOperation.getClass(), DisconnectOperation.class);
            
            FakeMedium disconnectMedium = new FakeMedium();
            disconnectOperation.medium = disconnectMedium;
            disconnectOperation.init();
            
            disconnectOperation.performOperation();
            assertEquals(Interactor.getInstance().getRouter().getInterfacesConnections().size(), 0);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
