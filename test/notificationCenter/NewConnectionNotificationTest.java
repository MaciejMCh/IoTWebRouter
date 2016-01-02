/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationCenter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
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
import requestOperations.Admin.ConnectOperation;
import requestOperations.Device.RegisterOperation;
import requestOperations.FakeMedium;

/**
 *
 * @author maciej
 */
public class NewConnectionNotificationTest {
    
    public NewConnectionNotificationTest() {
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
     * Test of notificationType method, of class NewConnectionNotification.
     */
    @Test
    public void testNotificationType() {
        NewConnectionNotification instance = new NewConnectionNotification(null);
        String expResult = "new_connection";
        String result = instance.notificationType();
        assertEquals(expResult, result);
    }

    /**
     * Test of subjectJsonRepresentation method, of class NewConnectionNotification.
     */
    @Test
    public void testSubjectJsonRepresentation() {
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
            ArrayList<InterfaceConnection> interfaceConnections = Interactor.getInstance().getRouter().getInterfacesConnections();
            
            NewConnectionNotification notification = new NewConnectionNotification(interfaceConnections.get(interfaceConnections.size() - 1));
            
            JsonObject expected = new JsonParser().parse("{\"output\":{\"interface_id\":\"in_1\",\"device_id\":\"" + outputDeviceID + "\"},\"input\":{\"interface_id\":\"in_0\",\"device_id\":\"" + inputDeviceID + "\"}}").getAsJsonObject();
            JsonObject actual = notification.subjectJsonRepresentation();
            
            assertEquals(expected, actual);
            
        } catch (SerializationErrorException ex) {
            fail(ex.toString());
        }
    }
    
}
