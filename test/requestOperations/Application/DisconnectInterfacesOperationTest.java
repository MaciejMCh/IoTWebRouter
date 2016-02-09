/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Application;

import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.sun.corba.se.impl.orbutil.ORBUtility;
import java.util.HashMap;
import model.Interactor;
import model.ModelSerializer;
import model.SerializableModel;
import model.SerializationErrorException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import requestOperations.Admin.ConnectOperation;
import requestOperations.Admin.DisconnectOperation;
import requestOperations.Device.RegisterOperation;
import requestOperations.FakeMedium;
import requestOperations.MobileRequestOperationsSerializer;

/**
 *
 * @author maciej
 */
public class DisconnectInterfacesOperationTest {
    
    public DisconnectInterfacesOperationTest() {
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
     * Test of JSONKeyPathsByPropertyKey method, of class DisconnectInterfacesOperation.
     */
    @Test
    public void testSerialization() {
        try {
            JsonObject json = new com.google.gson.JsonParser().parse("{\"request_id\":\"app_req_1\",\"action\":\"connect_interfaces\",\"connection\":{\"output_device_id\":\"dev_0\",\"output_interface_id\":\"int_0\",\"input_device_id\":\"dev_1\",\"input_interface_id\":\"int_1\"}}").getAsJsonObject();
            SerializableModel model = ModelSerializer.model(ConnectInterfacesOperation.class, json);
            
            assertNotNull(model);
            assertEquals(model.getClass(), ConnectInterfacesOperation.class);
            
            ConnectInterfacesOperation operation = (ConnectInterfacesOperation)model;
            
            assertNotNull(operation.workerOperation);
            assertEquals(operation.workerOperation.getOutputDeviceID(), "dev_0");
            assertEquals(operation.workerOperation.getOutputInterfaceID(), "int_0");
            assertEquals(operation.workerOperation.getInputDeviceID(), "dev_1");
            assertEquals(operation.workerOperation.getInputInterfaceID(), "int_1");
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testOperationPerform() {
        try {
            Interactor.getInstance().restart();
                    
            JsonObject inputRegisterJson = new com.google.gson.JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"in_0\"}]}}").getAsJsonObject();
            RegisterOperation inputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, inputRegisterJson);
            FakeMedium inputRegisterMedium = new FakeMedium();
            inputRegisterOperation.medium = inputRegisterMedium;
            inputRegisterOperation.performOperation();
            String inputDeviceID = inputRegisterOperation.getRegisteringDevice().getId();
            
            JsonObject outputRegisterJson = new com.google.gson.JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"sensor\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"in_1\"}]}}").getAsJsonObject();
            RegisterOperation outputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, outputRegisterJson);
            FakeMedium outputRegisterMedium = new FakeMedium();
            outputRegisterOperation.medium = outputRegisterMedium;
            outputRegisterOperation.performOperation();
            String outputDeviceID = outputRegisterOperation.getRegisteringDevice().getId();
            
            JsonObject json = new com.google.gson.JsonParser().parse("{\"action\":\"connect\",\"output_device_id\":\"" + outputDeviceID + "\",\"output_interface_id\":\"in_1\",\"input_device_id\":\"" + inputDeviceID + "\",\"input_interface_id\":\"in_0\"}").getAsJsonObject();
            ConnectOperation connectOperation = (ConnectOperation) ModelSerializer.model(ConnectOperation.class, json);
            FakeMedium connectMedium = new FakeMedium();
            connectOperation.medium = connectMedium;
            
            int connectionsCount = Interactor.getInstance().getRouter().getInterfacesConnections().size();
            
            assertEquals(Interactor.getInstance().getRouter().getInterfacesConnections().size(), 0);
            
            connectOperation.performOperation();
            
            assertEquals(Interactor.getInstance().getRouter().getInterfacesConnections().size(), 1);
            
            FakeMedium disconnectMedium = new FakeMedium();
            JsonObject disconnectJson = new com.google.gson.JsonParser().parse("{\"request_id\":\"app_req_1\",\"action\":\"disconnect_interfaces\",\"connection\":{\"output_device_id\":\""+outputDeviceID+"\",\"output_interface_id\":\"in_1\",\"input_device_id\":\""+inputDeviceID+"\",\"input_interface_id\":\"in_0\"}}").getAsJsonObject();
            
            DisconnectInterfacesOperation disconnectOperation = (DisconnectInterfacesOperation) new MobileRequestOperationsSerializer().serializeOperation(disconnectJson, disconnectMedium);
            disconnectOperation.performOperation();
            
            assertNotNull(disconnectOperation.workerOperation);
            
            
            assertNotNull(disconnectOperation.workerOperation.getInputInterface());
            assertNotNull(disconnectOperation.workerOperation.getOutputInterface());
            
            System.out.println("in test output is " + disconnectOperation.workerOperation.getInputInterface());
            
            assertEquals(disconnectMedium.message, "{\"result\":\"success\",\"request_id\":\"app_req_1\",\"response\":{}}");
            
            assertEquals(Interactor.getInstance().getRouter().getInterfacesConnections().size(), 0);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
}
