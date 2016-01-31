/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestOperations.Device;

import requestOperations.FakeMedium;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Device;
import model.Interactor;
import model.ModelSerializer;
import model.SerializationErrorException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import requestOperations.Admin.ConnectOperation;

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
            Device firstDevice = firstRegisterOperation.registeringDevice;
            
            int preDevicesCount = Interactor.getInstance().getEnviroment().devices.size();
            String storedID = firstRegisterOperation.getRegisteringDevice().getId();
            JsonObject json = new JsonParser().parse("{\"action\":\"register\",\"stored_id\":\"" + storedID + "\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"in_0\"}]}}").getAsJsonObject();
            
            RegisterOperation operation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, json);
            FakeMedium reconnectMedium = new FakeMedium();
            operation.medium = reconnectMedium;
            operation.performOperation();
            
            assertEquals(Interactor.getInstance().getEnviroment().devices.size() , preDevicesCount);
            
            Device secondDevice = operation.registeringDevice;
            
            assertNotNull(Interactor.getInstance().mediumOfDevice(firstDevice));
            assertNotNull(Interactor.getInstance().deviceForMedium(reconnectMedium));
            
            assertEquals(Interactor.getInstance().deviceForMedium(reconnectMedium), firstDevice);
            assertEquals(Interactor.getInstance().mediumOfDevice(firstDevice), reconnectMedium);
            
            assertNull(reconnectMedium.message);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void reconnectTest() {
        try {
            // connect input
            JsonObject firstInputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_0\"}]}}").getAsJsonObject();
            RegisterOperation firstInputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, firstInputRegisterJson);
            FakeMedium firstInputRegistrationMedium = new FakeMedium();
            firstInputRegisterOperation.medium = firstInputRegistrationMedium;
            firstInputRegisterOperation.performOperation();
            Device inputDevice = firstInputRegisterOperation.registeringDevice;
            
            // connect output
            JsonObject outputRegisterOperationJson = new JsonParser().parse("{\"action\":\"register\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"output\",\"data_type\":\"light\",\"id\":\"int_0\"}]}}").getAsJsonObject();
            RegisterOperation outputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, outputRegisterOperationJson);
            FakeMedium outputRegistrationMedium = new FakeMedium();
            outputRegisterOperation.medium = outputRegistrationMedium;
            outputRegisterOperation.performOperation();
            Device outputDevice = outputRegisterOperation.registeringDevice;
            
            // connect interfaces
            JsonObject json = new JsonParser().parse("{\"action\":\"connect\",\"output_device_id\":\"" + outputRegisterOperation.registeringDevice.getId() + "\",\"output_interface_id\":\"int_0\",\"input_device_id\":\"" + firstInputRegisterOperation.registeringDevice.getId() + "\",\"input_interface_id\":\"int_0\"}").getAsJsonObject();
            ConnectOperation connectOperation = (ConnectOperation) ModelSerializer.model(ConnectOperation.class, json);
            FakeMedium connectMedium = new FakeMedium();
            connectOperation.medium = connectMedium;
            connectOperation.performOperation();
            
            // send message
            JsonObject dataJson = new JsonParser().parse("{\"action\":\"data\",\"signals\":[{\"interface_id\":\"int_0\",\"message\":{\"data_type\":\"light\",\"value\":455}}]}").getAsJsonObject();
            DataOperation dataOperation = (DataOperation) ModelSerializer.model(DataOperation.class, dataJson);
            dataOperation.medium = outputRegistrationMedium;
            dataOperation.performOperation();
            
            assertEquals(firstInputRegistrationMedium.message, "{\"data_type\":\"light\",\"value\":\"455\"}");
            
            // device disconnected
            Interactor.getInstance().mediumClosed(firstInputRegistrationMedium);
            
            // send message to disconnected input
            JsonObject dataJson2 = new JsonParser().parse("{\"action\":\"data\",\"signals\":[{\"interface_id\":\"int_0\",\"message\":{\"data_type\":\"light\",\"value\":456}}]}").getAsJsonObject();
            DataOperation dataOperation2 = (DataOperation) ModelSerializer.model(DataOperation.class, dataJson2);
            dataOperation2.medium = outputRegistrationMedium;
            dataOperation2.performOperation();
            
            assertEquals(firstInputRegistrationMedium.message, "{\"data_type\":\"light\",\"value\":\"455\"}");
            
            // reconnect input
            JsonObject secondInputRegisterJson = new JsonParser().parse("{\"action\":\"register\",\"stored_id\":\"" + inputDevice.getId() + "\",\"device\":{\"name\":\"actuator\",\"interfaces\":[{\"direction\":\"input\",\"data_type\":\"light\",\"id\":\"int_0\"}]}}").getAsJsonObject();
            RegisterOperation secondInputRegisterOperation = (RegisterOperation) ModelSerializer.model(RegisterOperation.class, secondInputRegisterJson);
            FakeMedium secondInputRegistrationMedium = new FakeMedium();
            secondInputRegisterOperation.medium = secondInputRegistrationMedium;
            secondInputRegisterOperation.performOperation();
            
            assertNull(secondInputRegistrationMedium.message);
            
            // send message to reconnected device
            JsonObject dataJson3 = new JsonParser().parse("{\"action\":\"data\",\"signals\":[{\"interface_id\":\"int_0\",\"message\":{\"data_type\":\"light\",\"value\":555}}]}").getAsJsonObject();
            DataOperation dataOperation3 = (DataOperation) ModelSerializer.model(DataOperation.class, dataJson3);
            dataOperation3.medium = outputRegistrationMedium;
            dataOperation3.performOperation();
            
            assertEquals(firstInputRegistrationMedium.message, "{\"data_type\":\"light\",\"value\":\"455\"}");
            assertEquals(secondInputRegistrationMedium.message, "{\"data_type\":\"light\",\"value\":\"555\"}");
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
        
        
    }
    
    
}
